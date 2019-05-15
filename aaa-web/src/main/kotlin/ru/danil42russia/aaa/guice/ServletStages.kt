package ru.danil42russia.aaa.guice

import com.google.inject.servlet.ServletModule
import ru.danil42russia.aaa.servlet.AuthorityServlet
import ru.danil42russia.aaa.servlet.EchoServlet
import ru.danil42russia.aaa.servlet.RedirectServlet
import ru.danil42russia.aaa.servlet.UserServlet

open class ServletStages : ServletModule() {
    override fun configureServlets() {
        serve("/echo/get").with(EchoServlet::class.java)
        serve("/echo/post").with(RedirectServlet::class.java)

        serve("/ajax/user").with(UserServlet::class.java)
        serve("/ajax/authority").with(AuthorityServlet::class.java)
    }
}