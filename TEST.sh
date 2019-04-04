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

test "T0-1" 0 -login user@xyz.com -pass qwerty12345
test "T0-2" 0 -pass 123456qwerty -login user@mk.ru

test "T1-1" 1
test "T1-2" 1 -login user@xyz.com -pass qwerty12345 -params
test "T1-3" 1 -login user@xyz.com
test "T1-4" 1 -pass qwerty12345
test "T1-5" 1 -h

test "T2-1" 2 -login user -pass 1234
test "T2-2" 2 -login user@u -pass 1234

test "T3-1" 3 -login useD@xyz.zom -pass 1234
test "T3-2" 3 -login e@e.ru -pass 1234

test "T3-1" 4 -login user@xyz.com -pass 1234
test "T3-2" 4 -login user@mk.com -pass 1234

echo
if [ $COUNT_ALL -eq $COUNT_SUC ]; then
    echo All $COUNT_SUC test passed
    exit 0
else
    echo $COUNT_SUC success test out $COUNT_ALL
    exit 1
fi