package ru.danil42russia.aaa.servlet

import com.google.inject.Inject
import com.google.inject.Singleton
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.dao.entity.AuthorityDao
import ru.danil42russia.aaa.domain.data.entity.EntityAuthority
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Singleton
class AuthorityServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    @Inject
    private lateinit var dao: AuthorityDao

    @UnstableDefault
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/authority")

        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val json = when {
            request.getParameter("id") != null -> {
                val authorityList = dao.getAuthorityByID(request.getParameter("id").toInt())
                Json.stringify(EntityAuthority.serializer().list, authorityList)
            }
            request.getParameter("userId") != null -> {
                val authorityList = dao.getAuthorityByUserID(request.getParameter("userId").toInt())
                Json.stringify(EntityAuthority.serializer().list, authorityList)
            }
            else -> {
                val authorityList = dao.getAllAuthority()
                Json.stringify(EntityAuthority.serializer().list, authorityList)
            }
        }

        response.writer.write(json)
    }
}