#!/bin/bash

javac -d out app/Main.java app/core/*.java app/commands/*.java app/helpers/*.java app/modules/computer/*.java app/modules/general/*.java app/modules/ai/*.java app/platform/*.java

java -cp out app.Main
