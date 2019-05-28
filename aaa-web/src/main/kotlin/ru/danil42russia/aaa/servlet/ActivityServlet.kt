package ru.danil42russia.aaa.servlet

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.ActivityDao
import ru.danil42russia.aaa.domain.data.Activity
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import ru.danil42russia.aaa.service.DBService
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ActivityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger
    private val dbService = DBService()

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/activity")
        val connection = dbService.getConnection()

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val json = when {
            connection != null -> {
                val dao = ActivityDao(connection)
                when {
                    request.getParameter("id") != null -> {
                        val activityList = dao.getActivityByID(request.getParameter("id").toInt())
                        Json.stringify(Activity.serializer().list, activityList)
                    }
                    request.getParameter("authorityId") != null -> {
                        val activityList = dao.getActivityByAuthorityID(request.getParameter("authorityId").toInt())
                        Json.stringify(Activity.serializer().list, activityList)
                    }
                    else -> {
                        val activityList = dao.getAllActivity()
                        Json.stringify(Activity.serializer().list, activityList)
                    }
                }
            }
            else -> "[]"
        }

        response.writer.write(json)
    }
}