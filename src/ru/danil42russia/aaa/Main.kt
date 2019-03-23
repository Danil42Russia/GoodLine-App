package ru.danil42russia.aaa

/*
//Ctrl+J
//Ctrl+Shift+F12
//Ctrl+Shift+J
//Ctrl+Shift+L
//Ctrl+Shift+O
 */

fun main(args: Array<String>) {
    val status: Int
    when {
        args.isEmpty() -> {
            println("No args!!!")
            status = 0
        }
        args.size == 1 -> status = 1
        args.size == 2 -> status = 2
        else -> {
            args.forEach { println(it) }
            status = 100
        }
    }

    System.exit(status)
}