package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/authority")
        servletContext.getRequestDispatcher("/authority.html").forward(request, response)
    }
}