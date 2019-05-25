package ru.danil42russia.aaa.domain.data

data class Cmd(
    val login: String,
    val pass: String,
    val res: String,
    val role: String,
    val ds: String,
    val de: String,
    val vol: String,
    val help: Boolean
)