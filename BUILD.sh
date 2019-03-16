#!/usr/bin/env bash
rm -rf out
mkdir out

echo Begin Build
kotlinc -d out/aaa.jar -include-runtime src
echo End Build