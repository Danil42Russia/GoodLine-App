package ru.danil42russia.aaa.guice

import com.google.inject.AbstractModule
import com.google.inject.matcher.Matchers
import ru.danil42russia.aaa.dao.entity.ActivityDao
import ru.danil42russia.aaa.dao.entity.AuthorityDao
import ru.danil42russia.aaa.dao.entity.UserDao
import ru.danil42russia.aaa.guice.modules.entity.EntityManagerProvider
import ru.danil42russia.aaa.guice.modules.log.Log4JTypeListener
import ru.danil42russia.aaa.servlet.*
import javax.persistence.EntityManager

open class ModuleStages : AbstractModule() {
    override fun configure() {
        bind(EchoServlet::class.java).asEagerSingleton()
        bind(RedirectServlet::class.java).asEagerSingleton()

        bind(UserDao::class.java)
        bind(UserServlet::class.java)

        bind(AuthorityDao::class.java)
        bind(AuthorityServlet::class.java)

        bind(ActivityDao::class.java)
        bind(ActivityServlet::class.java)

        bindListener(Matchers.any(), Log4JTypeListener())

        bind(EntityManager::class.java).toProvider(EntityManagerProvider::class.java)
    }
}