package ru.danil42russia.aaa.domain.data.entity

import kotlinx.serialization.Serializable
import javax.persistence.*

@Suppress("DATA_CLASS_WITHOUT_PARAMETERS")
@Serializable
@Entity
@Table(name = "users_roles")
data class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10)
    val id: Int = 0

    @Column(name = "id_user", length = 10)
    val id_user: Int = 0

    @Column(name = "id_role", length = 10)
    val id_role: Int = 0

    @Column(name = "res")
    val res: String = ""

    @Version
    @Column(name = "version", length = 10)
    val version: Int = 0
}