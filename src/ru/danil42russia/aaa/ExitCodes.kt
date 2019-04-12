package ru.danil42russia.aaa

enum class ExitCodes(val code: Int) {
    SUCCESS(0),
    HELP(1),
    BADLOGINFORMAT(2),
    BADLOGIN(3),
    BADPASSWORD(4),
}