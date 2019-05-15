package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EchoServlet : HttpServlet() {
    private val log = LogManager.getLogger(RedirectServlet ::class.java)

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        log.debug("Open /echo/get")
        servletContext?.getRequestDispatcher("/echo.jsp")?.forward(req, resp)
    }
}