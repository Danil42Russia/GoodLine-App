package ru.danil42russia.aaa.domain

import kotlinx.serialization.Serializable

@Serializable
data class Authority(
    val id: Int,
    val id_user: Int,
    val id_role: Int,
    val res: String
)