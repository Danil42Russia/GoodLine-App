package ru.danil42russia.aaa.domain.enums

enum class ExitCode(val code: Int) {
    NO_AUTH(-1),
    SUCCESS(0),
    HELP(1),
    BAD_LOGIN_FORMAT(2),
    BAD_LOGIN(3),
    BAD_PASSWORD(4),
    BAD_ROLE(5),
    NOT_PERMISSION(6),
    INVALID_DATE(7),
    INVALID_VOLUME(8),
    OTHER(255),
}