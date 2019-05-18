package ru.danil42russia.aaa.guice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import ru.danil42russia.aaa.service.MigrationService

@Suppress("unused")
class GuiceServletConfig : GuiceServletContextListener() {
    override fun getInjector(): Injector {
        //log.debug("Init Guice")
        val migrationService = MigrationService()
        migrationService.migrate()

        return Guice.createInjector(ServletStages(), ModuleStages())
    }
}