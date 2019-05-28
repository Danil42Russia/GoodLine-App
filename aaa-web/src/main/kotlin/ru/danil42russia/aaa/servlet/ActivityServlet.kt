package ru.danil42russia.aaa.servlet

import com.google.inject.Inject
import com.google.inject.Singleton
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.entity.ActivityDao
import ru.danil42russia.aaa.domain.data.entity.EntityActivity
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Singleton
class ActivityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    @Inject
    private lateinit var dao: ActivityDao

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/activity")

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val json = when {
            request.getParameter("id") != null -> {
                val activityList = dao.getActivityByID(request.getParameter("id").toInt())
                Json.stringify(EntityActivity.serializer().list, activityList)
            }
            request.getParameter("authorityId") != null -> {
                val activityList = dao.getActivityByAuthorityID(request.getParameter("authorityId").toInt())
                Json.stringify(EntityActivity.serializer().list, activityList)
            }
            else -> {
                val activityList = dao.getAllActivity()
                Json.stringify(EntityActivity.serializer().list, activityList)
            }
        }

        response.writer.write(json)
    }
}