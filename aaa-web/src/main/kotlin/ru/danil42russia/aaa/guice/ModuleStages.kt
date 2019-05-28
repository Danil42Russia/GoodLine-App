package ru.danil42russia.aaa.guice

import com.google.inject.AbstractModule
import com.google.inject.matcher.Matchers
import com.google.inject.persist.jpa.JpaPersistModule
import ru.danil42russia.aaa.dao.AuthorityDao
import ru.danil42russia.aaa.dao.UserDao
import ru.danil42russia.aaa.guice.modules.log.Log4JTypeListener
import ru.danil42russia.aaa.servlet.*

open class ModuleStages : AbstractModule() {
    override fun configure() {
        bind(EchoServlet::class.java).asEagerSingleton()
        bind(RedirectServlet::class.java).asEagerSingleton()

        bind(UserDao::class.java)
        bind(UserServlet::class.java)

        bind(AuthorityDao::class.java)
        bind(AuthorityServlet::class.java)

        bind(ActivityServlet::class.java).asEagerSingleton()

        bindListener(Matchers.any(), Log4JTypeListener())

        install(JpaPersistModule("myFirstJpaUnit"))
    }
}