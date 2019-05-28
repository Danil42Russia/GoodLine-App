package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import javax.persistence.*

@Serializable
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int,

    @Column(name = "login")
    val login: String,

    @Column(name = "pass")
    @Transient
    val pass: String = "",

    @Column(name = "salt")
    @Transient
    val salt: String = "",

    @Version
    @Column(name = "version")
    val version: Int = 0
)