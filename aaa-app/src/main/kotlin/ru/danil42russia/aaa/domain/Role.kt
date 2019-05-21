package ru.danil42russia.aaa.domain

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val id: Int,
    val name: String
)