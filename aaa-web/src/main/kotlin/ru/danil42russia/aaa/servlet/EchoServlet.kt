package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EchoServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /echo/get")
        servletContext.getRequestDispatcher("/echo.jsp").forward(request, response)
    }
}