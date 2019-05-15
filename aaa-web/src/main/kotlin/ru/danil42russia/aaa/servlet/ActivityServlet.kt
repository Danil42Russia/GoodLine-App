package ru.danil42russia.aaa.servlet

import org.apache.logging.log4j.LogManager
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ActivityServlet : HttpServlet() {
    private val log = LogManager.getLogger(ActivityServlet ::class.java)

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        log.debug("Open /ajax/activity")
        servletContext?.getRequestDispatcher("/activity.html")?.forward(req, resp)
    }
}