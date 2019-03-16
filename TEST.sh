#!/usr/bin/env bash
clear
./BUILD.sh

echo
echo TEST1
./RUN.sh

echo
echo TEST2
./RUN.sh foo bar baz "1 2 3"