package ru.danil42russia.aaa

//Ctrl+J
//Ctrl+Shift+F12
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No args!!!")
    } else {
        for (arg in args) {
            println(arg)
        }
    }
}