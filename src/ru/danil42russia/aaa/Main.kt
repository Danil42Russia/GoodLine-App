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
        System.exit(0)
    } else if (args.size == 1) {
        System.exit(1)
    } else if (args.size == 2) {
        System.exit(2)
    } else {
        args.forEach { println(it) }
        System.exit(100)
    }
}