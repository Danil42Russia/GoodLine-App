package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val id: Int,
    val id_ur: Int,
    val ds: String,
    val de: String,
    val vol: Int
)