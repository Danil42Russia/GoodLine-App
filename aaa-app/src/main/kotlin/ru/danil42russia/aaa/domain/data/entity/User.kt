package ru.danil42russia.aaa.domain.data.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import javax.persistence.*

@Suppress("DATA_CLASS_WITHOUT_PARAMETERS")
@Serializable
@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10)
    var id: Int = 0

    @Column(name = "login", length = 32, unique = true)
    var login: String = ""

    @Column(name = "pass", length = 64)
    @Transient
    var pass: String = ""

    @Column(name = "salt", length = 64)
    @Transient
    var salt: String = ""

    @Version
    @Column(name = "version", length = 10)
    val version: Int = 0
}