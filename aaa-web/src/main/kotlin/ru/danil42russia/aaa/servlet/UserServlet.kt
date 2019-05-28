package ru.danil42russia.aaa.servlet

import com.google.inject.Inject
import com.google.inject.Singleton
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.UserDao
import ru.danil42russia.aaa.domain.data.entity.EntityUser
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Singleton
class UserServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    @Inject
    private lateinit var dao: UserDao

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/user")

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val json = when {
            request.getParameter("id") == null -> {
                val users = dao.getAllUsers()
                Json.stringify(EntityUser.serializer().list, users)
            }
            else -> {
                val user = dao.getUserByID(request.getParameter("id").toInt())
                Json.stringify(EntityUser.serializer(), user)
            }
        }

        response.writer.write(json)
    }
}