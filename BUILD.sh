#!/usr/bin/env bash
rm -rf out
mkdir out

kotlinc -d out/aaa.jar -include-runtime src