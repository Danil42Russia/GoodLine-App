package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable
import javax.persistence.*

@Serializable
@Entity
@Table(name = "users_roles")
data class Authority(
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int,

    @Column(name = "id_user")
    val id_user: Int,

    @Column(name = "id_role")
    val id_role: Int,

    @Column(name = "res")
    val res: String
)