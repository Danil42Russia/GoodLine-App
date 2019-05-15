package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorityServlet : HttpServlet() {
    private val log = LogManager.getLogger(AuthorityServlet::class.java)

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        log.debug("Open /ajax/authority")
        servletContext?.getRequestDispatcher("/authority.html")?.forward(req, resp)
    }
}