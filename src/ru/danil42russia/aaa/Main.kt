package ru.danil42russia.aaa

/*
//Ctrl+J
//Ctrl+Shift+F12
//Ctrl+Shift+J
//Ctrl+Shift+L
//Ctrl+Shift+O
 */

fun main(args: Array<String>) {
    val status= when {
        args.isEmpty() -> {
            println("No args!!!")
            0
        }
        args.size == 1 -> 1
        args.size == 2 -> 2
        else -> {
            args.forEach { println(it) }
            100
        }
    }

    System.exit(status)
}