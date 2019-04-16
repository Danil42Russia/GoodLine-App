package ru.danil42russia.aaa.domain

enum class ExitCodes(val code: Int) {
    SUCCESS(0),
    HELP(1),
    BADLOGINFORMAT(2),
    BADLOGIN(3),
    BADPASSWORD(4),
    BADROLE(5),
    NOTPERMISSION(6),
    INCORRECTACTIVITY(7),
}