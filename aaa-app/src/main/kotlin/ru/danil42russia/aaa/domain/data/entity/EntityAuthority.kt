package ru.danil42russia.aaa.domain.data.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import javax.persistence.*

@Suppress("DATA_CLASS_WITHOUT_PARAMETERS")
@Serializable
@Entity(name = "authority")
@Table(name = "users_roles")
data class EntityAuthority {
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
    @Transient
    val version: Int = 0
}