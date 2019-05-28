package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val login: String,
    @Transient
    val pass: String = "",
    @Transient
    val salt: String = ""
)