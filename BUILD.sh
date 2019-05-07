#!/usr/bin/env bash
rm -rf out
mkdir out

kotlinc -d out/aaa.jar -include-runtime src -cp "libs/commons-cli-1.4.jar:libs/log4j-api-2.11.2.jar:libs/log4j-core-2.11.2.jar:libs/flyway-core-4.2.0.jar:libs/sqlite-jdbc-3.27.2.jar"