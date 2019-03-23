#!/usr/bin/env bash
clear
./BUILD.sh

echo
echo NO args
./RUN.sh
RES=$?
if [ $? -eq 0 ]; then
    echo Test passed
else
    echo Test failed. Expected 0 Actual $RES
fi

echo
echo 1 args
./RUN.sh foo
RES=$?
if [ $? -eq 1 ]; then
    echo Test passed
else
    echo Test failed. Expected 1 Actual $RES
fi

echo
echo 2 args
./RUN.sh foo bar
RES=$?
if [ $? -eq 2 ]; then
    echo Test passed
else
    echo Test failed. Expected 2 Actual $RES
fi

echo
echo Many args
./RUN.sh foo bar baz "1 2 3"
RES=$?
if [ $? -eq 100 ]; then
    echo Test passed
else
    echo Test failed. Expected 100 Actual $RES
fi