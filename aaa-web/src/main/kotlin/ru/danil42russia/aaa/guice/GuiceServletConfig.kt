package ru.danil42russia.aaa.guice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import org.apache.logging.log4j.LogManager

class GuiceServletConfig : GuiceServletContextListener() {
    private val log = LogManager.getLogger(GuiceServletConfig ::class.java)

    override fun getInjector(): Injector {
        log.debug("Init Guice")
        return Guice.createInjector(ServletStages(), ModuleStages())
    }
}