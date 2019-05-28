package ru.danil42russia.aaa.guice

import com.google.inject.persist.PersistFilter
import com.google.inject.servlet.ServletModule
import ru.danil42russia.aaa.servlet.*

open class ServletStages : ServletModule() {
    override fun configureServlets() {
        serve("/echo/get").with(EchoServlet::class.java)
        serve("/echo/post").with(RedirectServlet::class.java)

        serve("/ajax/user").with(UserServlet::class.java)
        serve("/ajax/authority").with(AuthorityServlet::class.java)
        serve("/ajax/activity").with(ActivityServlet::class.java)

        filter("/*").through(PersistFilter::class.java)
    }
}