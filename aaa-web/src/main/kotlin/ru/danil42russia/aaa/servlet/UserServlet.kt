package ru.danil42russia.aaa.servlet

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.domain.data.User
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import ru.danil42russia.aaa.service.DBService
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger
    private val dbService = DBService()

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/user")

        val connection = dbService.getConnection()

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        if (connection != null) {
            val dao = AuthenticationDao(connection)
            val json: String

            json = when {
                request.getParameter("id") == null -> {
                    val users = dao.getAllUsers()
                    Json.stringify(User.serializer().list, users)
                }
                else -> {
                    val user = dao.getUserByID(request.getParameter("id").toInt())
                    Json.stringify(User.serializer(), user)
                }
            }

            response.writer.write(json)
        } else {
            response.writer.write("[]")
        }
    }
}