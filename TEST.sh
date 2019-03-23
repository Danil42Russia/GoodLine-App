#!/usr/bin/env bash
clear
./BUILD.sh

COUNT_ALL=0
COUNT_SUC=0

echo
echo NO args
./RUN.sh
RES=$?
let COUNT_ALL++
if [ $RES -eq 0 ]; then
    echo Test passed
    let "COUNT_SUC = $COUNT_SUC + 1"
else
    echo Test failed. Expected 0 Actual $RES
fi

echo
echo 1 args
./RUN.sh foo
RES=$?
let COUNT_ALL++
if [ $RES -eq 1 ]; then
    echo Test passed
    let "COUNT_SUC = $COUNT_SUC + 1"
else
    echo Test failed. Expected 1 Actual $RES
fi

echo
echo 2 args
./RUN.sh foo bar
RES=$?
let COUNT_ALL++
if [ $RES -eq 2 ]; then
    echo Test passed
    let "COUNT_SUC = $COUNT_SUC + 1"
else
    echo Test failed. Expected 2 Actual $RES
fi

echo
echo Many args
./RUN.sh foo bar baz "1 2 3"
RES=$?
let COUNT_ALL++
if [ $RES -eq 100 ]; then
    echo Test passed
    let "COUNT_SUC = $COUNT_SUC + 1"
else
    echo Test failed. Expected 100 Actual $RES
fi

if [ $COUNT_ALL -eq $COUNT_SUC ]; then
    echo All $COUNT_SUC test passed
    exit 0
else
    echo $COUNT_SUC success test out $COUNT_ALL
    exit 1
fi