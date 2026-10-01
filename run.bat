```bat
@echo off
setlocal enabledelayedexpansion

rmdir /s /q out 2>nul
mkdir out

set "FILES="

for /r app %%f in (*.java) do (
    set "FILES=!FILES! "%%f""
)

javac -d out %FILES%

if errorlevel 1 (
    echo.
    echo Compilation failed.
    pause
    exit /b 1
)

java -cp out app.Main

endlocal
```
