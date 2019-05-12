package ru.danil42russia.aaa.servlet

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EchoServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        try {
            req?.getRequestDispatcher("/echo.jsp")?.forward(req, resp)
        } catch (ex: ServletException) {
        }
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        try {
            req?.getRequestDispatcher("/echo.jsp")?.forward(req, resp)
        } catch (ex: ServletException) {
        }
    }
}