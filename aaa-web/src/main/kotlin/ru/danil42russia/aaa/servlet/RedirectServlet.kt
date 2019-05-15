package ru.danil42russia.aaa.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RedirectServlet : HttpServlet() {
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.sendRedirect("get?X=${req?.getParameter("X")}")
    }
}