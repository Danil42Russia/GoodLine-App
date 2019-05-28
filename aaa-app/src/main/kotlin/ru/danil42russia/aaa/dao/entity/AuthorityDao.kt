package ru.danil42russia.aaa.dao.entity

import com.google.inject.Inject
import ru.danil42russia.aaa.domain.data.entity.EntityAuthority
import javax.persistence.EntityManager

class AuthorityDao {
    @Inject
    lateinit var em: EntityManager

    fun getAllAuthority(): List<EntityAuthority> {
        return em
            .createQuery(
                "select a FROM authority a",
                EntityAuthority::class.java
            )
            .resultList
    }

    fun getAuthorityByID(id: Int): List<EntityAuthority> {
        return em
            .createQuery(
                "select a FROM authority a WHERE a.id = :id",
                EntityAuthority::class.java
            )
            .setParameter("id", id)
            .resultList
    }

    fun getAuthorityByUserID(userId: Int): List<EntityAuthority> {
        return em
            .createQuery(
                "select a FROM authority a WHERE a.id_user = :id_user",
                EntityAuthority::class.java
            )
            .setParameter("id_user", userId)
            .resultList
    }
}