package ru.danil42russia.aaa

/*
//Ctrl+J
//Ctrl+Shift+F12
//Ctrl+Shift+J
//Ctrl+Shift+L
//Ctrl+Shift+O
 */

fun main(args: Array<String>) {
    when {
        args.isEmpty() -> {
            println("No args!!!")
            System.exit(0)
        }
        args.size == 1 -> System.exit(1)
        args.size == 2 -> System.exit(2)
        else -> {
            args.forEach { println(it) }
            System.exit(100)
        }
    }
}