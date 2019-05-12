package ru.danil42russia.aaa.domain

import java.time.LocalDate

data class Cmd(
    val login: String,
    val pass: String,
    val res: String,
    val role: String,
    val ds: LocalDate,
    val de: LocalDate,
    val vol: Int,
    val help: Boolean
)