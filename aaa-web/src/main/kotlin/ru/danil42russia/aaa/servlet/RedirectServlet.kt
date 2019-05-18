package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RedirectServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("redirect to /echo/get?X=${request.getParameter("X")}")
        response.sendRedirect("get?X=${request.getParameter("X")}")
    }
}