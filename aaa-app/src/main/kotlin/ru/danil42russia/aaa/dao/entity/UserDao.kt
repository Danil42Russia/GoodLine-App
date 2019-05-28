package ru.danil42russia.aaa.dao.entity

import com.google.inject.Inject
import ru.danil42russia.aaa.domain.data.entity.EntityUser
import javax.persistence.EntityManager

class UserDao {
    @Inject
    lateinit var em: EntityManager

    fun getAllUsers(): List<EntityUser> {
        return em
            .createQuery("select u from user u", EntityUser::class.java)
            .resultList
    }

    fun getUserByID(id: Int): EntityUser {
        return em
            .createQuery("SELECT u FROM user u WHERE u.id = :id ", EntityUser::class.java)
            .setParameter("id", id)
            .singleResult
    }
}