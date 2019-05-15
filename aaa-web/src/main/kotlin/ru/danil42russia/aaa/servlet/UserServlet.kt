package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserServlet : HttpServlet() {
    private val log = LogManager.getLogger(UserServlet::class.java)

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        log.debug("Open /ajax/user")
        servletContext?.getRequestDispatcher("/user.html")?.forward(req, resp)
    }
}