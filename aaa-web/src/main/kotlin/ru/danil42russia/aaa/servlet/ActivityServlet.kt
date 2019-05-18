package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ActivityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        logger.debug("Open /ajax/activity")
        servletContext?.getRequestDispatcher("/activity.html")?.forward(req, resp)
    }
}