package ru.danil42russia.aaa.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EchoServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        servletContext?.getRequestDispatcher("/echo.jsp")?.forward(req, resp)
    }
}