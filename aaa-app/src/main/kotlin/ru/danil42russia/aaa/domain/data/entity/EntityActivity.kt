package ru.danil42russia.aaa.domain.data.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import javax.persistence.*

@Suppress("DATA_CLASS_WITHOUT_PARAMETERS")
@Serializable
@Entity(name = "activity")
@Table(name = "activity")
data class EntityActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10)
    val id: Int = 0

    @Column(name = "id_ur", length = 10)
    val id_ur: Int = 0

    @Column(name = "ds", length = 16)
    val ds: String = ""

    @Column(name = "de", length = 16)
    val de: String = ""

    @Column(name = "vol", length = 10)
    val vol: Int = 0

    @Version
    @Column(name = "version", length = 10)
    @Transient
    val version: Int = 0
}