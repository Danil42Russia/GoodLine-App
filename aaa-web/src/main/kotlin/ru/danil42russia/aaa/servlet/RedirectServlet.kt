package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RedirectServlet : HttpServlet() {
    private val log = LogManager.getLogger(RedirectServlet::class.java)

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        log.debug("redirect to /echo/get?X=${req?.getParameter("X")}")
        resp?.sendRedirect("get?X=${req?.getParameter("X")}")
    }
}