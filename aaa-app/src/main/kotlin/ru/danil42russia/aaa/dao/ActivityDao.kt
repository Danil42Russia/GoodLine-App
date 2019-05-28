package ru.danil42russia.aaa.dao

import com.google.inject.Inject
import ru.danil42russia.aaa.domain.data.entity.EntityActivity
import javax.persistence.EntityManager

class ActivityDao {
    @Inject
    lateinit var em: EntityManager

    fun getAllActivity(): List<EntityActivity> {
        return em
            .createQuery("SELECT a FROM activity a ", EntityActivity::class.java)
            .resultList
    }

    fun getActivityByID(id: Int): List<EntityActivity> {
        return em
            .createQuery("SELECT a FROM activity a  WHERE a.id = :id ", EntityActivity::class.java)
            .setParameter("id", id)
            .resultList
    }

    fun getActivityByAuthorityID(authorityId: Int): List<EntityActivity> {
        return em
            .createQuery("SELECT a FROM activity a  WHERE a.id_ur = :id_ur ", EntityActivity::class.java)
            .setParameter("id_ur", authorityId)
            .resultList
    }
}