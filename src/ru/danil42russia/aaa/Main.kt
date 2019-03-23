package ru.danil42russia.aaa

/*
//Ctrl+J
//Ctrl+Shift+F12
//Ctrl+Shift+J
//Ctrl+Shift+L
//Ctrl+Shift+O
 */

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No args!!!")
    } else {
        args.forEach { println(it) }
    }
}