#!/usr/bin/env bash
echo Begin Build
kotlinc -d out/jar.jar -include-runtime src
echo End Build