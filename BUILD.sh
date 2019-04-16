#!/usr/bin/env bash
rm -rf out
mkdir out

kotlinc -d out/aaa.jar -include-runtime src -cp libs/commons-cli-1.4.jar