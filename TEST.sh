#!/usr/bin/env bash
clear
clear
./BUILD.sh

COUNT_ALL=0
COUNT_SUC=0

function test {
    MSG=$1; shift
    EXPECTED=$1; shift

    echo
    echo $MSG
    ./RUN.sh "$@"
    RES=$?
    let COUNT_ALL++
    if [ $RES -eq $EXPECTED ]; then
        echo Test passed
        let "COUNT_SUC = $COUNT_SUC + 1"
    else
        echo Test failed. Expected $EXPECTED Actual $RES
    fi
}

echo
if [ $COUNT_ALL -eq $COUNT_SUC ]; then
    echo All $COUNT_SUC test passed
    exit 0
else
    echo $COUNT_SUC success test out $COUNT_ALL
    exit 1
fi