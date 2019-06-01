package ru.danil42russia.aaa.guice.modules.entity

import java.util.*
import javax.inject.Provider
import javax.persistence.EntityManager
import javax.persistence.Persistence

class EntityManagerProvider : Provider<EntityManager> {
    private val property = Properties()

    init {
        property["hibernate.dialect"] = System.getenv("DB_DIALECT")
        property["hibernate.connection.driver_class"] = System.getenv("DB_CLASS")
        property["hibernate.connection.url"] = System.getenv("DB_URL")
        property["hibernate.connection.user"] = System.getenv("DB_LOGIN")
        property["hibernate.connection.password"] = System.getenv("DB_PASSWORD")
    }

    private val entityManagerFactory = Persistence.createEntityManagerFactory("myFirstJpaUnit", property)

    override fun get(): EntityManager {
        return entityManagerFactory.createEntityManager()
    }
}