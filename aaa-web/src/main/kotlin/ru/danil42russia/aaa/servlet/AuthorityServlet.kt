package ru.danil42russia.aaa.servlet

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.AuthorizationDao
import ru.danil42russia.aaa.domain.Authority
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import ru.danil42russia.aaa.service.DBService
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger
    private val dbService = DBService()

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/authority")

        val connection = dbService.getConnection()

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        val json: String

        json = when {
            connection != null -> {
                val dao = AuthorizationDao(connection)
                when {
                    request.getParameter("id") != null -> {
                        val authorityList = dao.getAuthorityByID(request.getParameter("id").toInt())
                        Json.stringify(Authority.serializer().list, authorityList)
                    }
                    request.getParameter("userId") != null -> {
                        val authorityList = dao.getAuthorityByUserID(request.getParameter("userId").toInt())
                        Json.stringify(Authority.serializer().list, authorityList)
                    }
                    else -> {
                        val authorityList = dao.getAllAuthority()
                        Json.stringify(Authority.serializer().list, authorityList)
                    }
                }
            }
            else -> "[]"
        }

        response.writer.write(json)
    }
}