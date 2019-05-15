package ru.danil42russia.aaa.guice

import com.google.inject.servlet.ServletModule
import ru.danil42russia.aaa.servlet.EchoServlet
import ru.danil42russia.aaa.servlet.RedirectServlet

open class ServletStages : ServletModule() {
    override fun configureServlets() {
        serve("/echo/get").with(EchoServlet::class.java)
        serve("/echo/post").with(RedirectServlet::class.java)
    }
}