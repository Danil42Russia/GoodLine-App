package ru.danil42russia.aaa.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class User(
    val id: Int,
    val login: String,
    @Transient
    val pass: String = "",
    @Transient
    val salt: String = ""
)