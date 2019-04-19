package ru.danil42russia.aaa.domain

enum class ExitCode(val code: Int) {
    NOAUTH(-1),
    SUCCESS(0),
    HELP(1),
    BADLOGINFORMAT(2),
    BADLOGIN(3),
    BADPASSWORD(4),
    BADROLE(5),
    NOTPERMISSION(6),
    INCORRECTACTIVITY(7),
}