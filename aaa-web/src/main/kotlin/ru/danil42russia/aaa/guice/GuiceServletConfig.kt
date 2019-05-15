package ru.danil42russia.aaa.guice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener

class GuiceServletConfig : GuiceServletContextListener() {
    override fun getInjector(): Injector {
        return Guice.createInjector(ServletStages(), ModuleStages())
    }
}