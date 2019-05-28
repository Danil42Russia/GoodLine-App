package ru.danil42russia.aaa.domain.data

import kotlinx.serialization.Serializable
import javax.persistence.*

@Serializable
@Entity
@Table(name = "activity")
data class Activity(
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Int,

    @Column(name = "id_ur")
    val id_ur: Int,

    @Column(name = "ds")
    val ds: String,

    @Column(name = "de")
    val de: String,

    @Column(name = "vol")
    val vol: Int,

    @Version
    @Column(name = "version")
    val version: Int = 0
)