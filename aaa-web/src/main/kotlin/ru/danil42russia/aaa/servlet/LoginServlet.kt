package ru.danil42russia.aaa.servlet

import com.google.inject.Singleton
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.apache.logging.log4j.Logger
import ru.danil42russia.aaa.application
import ru.danil42russia.aaa.domain.data.Cmd
import ru.danil42russia.aaa.guice.modules.log.InjectLogger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Singleton
class LoginServlet : HttpServlet() {
    @InjectLogger
    private lateinit var logger: Logger

    @UnstableDefault
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        logger.debug("Open /ajax/login")

        val json = request.getParameter("json")
        val obj = Json.parse(Cmd.serializer(), json)

        logger.debug("json parse: $obj")

        var args = ""

        if (obj.login != "") {
            args += " -login "
            args += obj.login
        }
        if (obj.pass != "") {
            args += " -pass "
            args += obj.pass
        }

        if (obj.res != "") {
            args += " -res "
            args += obj.res
        }
        if (obj.role != "") {
            args += " -role "
            args += obj.role
        }

        if (obj.ds != "") {
            args += " -ds "
            args += obj.ds
        }
        if (obj.de != "") {
            args += " -de "
            args += obj.de
        }
        if (obj.vol != "") {
            args += " -vol "
            args += obj.vol
        }

        logger.debug("args: $args")
        val code = application(args.toArray())

        response.contentType = "text/html"
        response.characterEncoding = "UTF-8"

        response.writer.write(code.toString())
        logger.debug("code: $code")
    }

    private fun String.toArray(): Array<String> {
        return this.split(' ').toTypedArray()
    }
}
