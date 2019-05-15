package ru.danil42russia.aaa.guice

import com.google.inject.AbstractModule
import ru.danil42russia.aaa.servlet.EchoServlet
import ru.danil42russia.aaa.servlet.RedirectServlet

open class ModuleStages : AbstractModule() {
    override fun configure() {
        bind(EchoServlet::class.java).asEagerSingleton()
        bind(RedirectServlet::class.java).asEagerSingleton()
    }
}