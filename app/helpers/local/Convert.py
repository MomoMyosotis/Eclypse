# first line

from __future__ import annotations

import argparse
import sys
from pathlib import Path
from xml.sax.saxutils import escape


IMAGE_FORMATS = {"jpeg", "jpg", "png"}
VIDEO_FORMATS = {
    "3gp", "avi", "flv", "m4v", "mkv", "mov", "mp4", "mpeg", "mpg", "webm", "wmv"
}


def output_path(source: Path, target_format: str) -> Path:
    return source.with_suffix(f".{target_format}")


def convert_image(source: Path, target: Path) -> None:
    try:
        from PIL import Image
    except ImportError as error:
        raise RuntimeError("image conversion requires Pillow: python -m pip install Pillow") from error

    with Image.open(source) as image:
        if image.mode not in ("RGB", "L"):
            image = image.convert("RGB")
        if target.suffix.lower() == ".pdf":
            image.save(target, "PDF", resolution=100.0)
        else:
            image.save(target, "JPEG" if target.suffix.lower() in {".jpg", ".jpeg"} else "PNG")


def convert_pdf(source: Path, target: Path) -> None:
    try:
        import fitz
    except ImportError as error:
        raise RuntimeError("PDF conversion requires PyMuPDF: python -m pip install PyMuPDF") from error

    document = fitz.open(source)
    try:
        if not document.page_count:
            raise RuntimeError("PDF contains no pages")
        pixmap = document[0].get_pixmap(matrix=fitz.Matrix(2, 2), alpha=False)
        pixmap.save(target)
    finally:
        document.close()


def convert_odt(source: Path, target: Path) -> None:
    try:
        from odf import text
        from odf.opendocument import load
        from reportlab.lib.pagesizes import A4
        from reportlab.lib.styles import getSampleStyleSheet
        from reportlab.platypus import Paragraph, SimpleDocTemplate, Spacer
    except ImportError as error:
        raise RuntimeError(
            "ODT conversion requires odfpy and reportlab: "
            "python -m pip install odfpy reportlab"
        ) from error

    document = load(source)
    styles = getSampleStyleSheet()
    content = []
    for paragraph in document.getElementsByType(text.P):
        value = "".join(node.data for node in paragraph.childNodes if hasattr(node, "data")).strip()
        if value:
            content.append(Paragraph(escape(value), styles["BodyText"]))
            content.append(Spacer(1, 8))
    SimpleDocTemplate(str(target), pagesize=A4).build(content)


def convert_video(source: Path, target: Path) -> None:
    try:
        import av
    except ImportError as error:
        raise RuntimeError("video conversion requires PyAV: python -m pip install av") from error

    with av.open(source) as input_container, av.open(target, mode="w") as output_container:
        audio_stream = next((stream for stream in input_container.streams if stream.type == "audio"), None)
        if audio_stream is None:
            raise RuntimeError("video contains no audio stream")
        output_stream = output_container.add_stream("mp3", rate=audio_stream.rate or 44100)
        try:
            for frame in input_container.decode(audio_stream):
                for packet in output_stream.encode(frame):
                    output_container.mux(packet)
            for packet in output_stream.encode():
                output_container.mux(packet)
        except Exception as e:
            print (f"something went wrong in video conversion\n{e}");
            return 1


def convert(path: str, target_format: str) -> Path:
    source = Path(path).expanduser().resolve()
    if not source.is_file():
        raise RuntimeError(f"file not found: {source}")

    source_format = source.suffix.lower().lstrip(".")
    target_format = target_format.lower().lstrip(".")
    target = output_path(source, target_format)
    if target == source:
        raise RuntimeError("source and target formats are the same")

    if source_format in IMAGE_FORMATS and target_format == "pdf":
        convert_image(source, target)
    elif source_format == "pdf" and target_format in IMAGE_FORMATS:
        convert_pdf(source, target)
    elif source_format == "odt" and target_format == "pdf":
        convert_odt(source, target)
    elif source_format in VIDEO_FORMATS and target_format == "mp3":
        convert_video(source, target)
    else:
        raise RuntimeError(f"conversion from .{source_format} to .{target_format} is not supported")

    if not target.is_file():
        raise RuntimeError(f"conversion completed without creating {target}")
    return target


def main() -> int:
    parser = argparse.ArgumentParser(description="Convert a file to another format.")
    parser.add_argument("path")
    parser.add_argument("target_format")
    arguments = parser.parse_args()

    try:
        target = convert(arguments.path, arguments.target_format)
    except (OSError, RuntimeError) as error:
        print(f"conversion failed: {error}", file=sys.stderr)
        return 1

    print(target)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

# last line