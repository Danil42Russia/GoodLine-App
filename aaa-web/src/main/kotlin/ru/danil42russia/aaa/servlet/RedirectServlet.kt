package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RedirectServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        logger.debug("redirect to /echo/get?X=${req?.getParameter("X")}")
        resp?.sendRedirect("get?X=${req?.getParameter("X")}")
    }
}