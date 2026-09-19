@echo off

rmdir /s /q out 2>nul
mkdir out

for /r app %%f in (*.java) do (
    echo %%f
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