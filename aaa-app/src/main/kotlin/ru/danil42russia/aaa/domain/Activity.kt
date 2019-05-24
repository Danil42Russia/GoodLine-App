package ru.danil42russia.aaa.domain

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val id: Int,
    val login: String,
    val res: String,
    val role: String,
    val ds: String,
    val de: String,
    val vol: String
)