package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Cmd(
    val login: String,
    val pass: String,
    val res: String,
    val role: String,
    val ds: String,
    val de: String,
    val vol: String,
    @Transient
    val help: Boolean = false
)