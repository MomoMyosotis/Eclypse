#!/bin/bash

rm -rf out
mkdir -p out
javac -d out $(find app -name '*.java' -type f)

java -cp out app.Main
