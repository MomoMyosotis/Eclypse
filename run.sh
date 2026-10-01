#!/bin/bash

# Clean previous compilation
rm -rf out
mkdir -p out

# Compile every Java source file under app/
find app -name "*.java" -print0 | xargs -0 javac -d out

if [ $? -ne 0 ]; then
    echo
    echo "Compilation failed."
    exit 1
fi

# Run Eclypse
java -cp out app.Main

