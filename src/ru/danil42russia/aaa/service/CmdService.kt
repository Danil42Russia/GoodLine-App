package ru.danil42russia.aaa.service

import org.apache.commons.cli.*
import ru.danil42russia.aaa.domain.Cmd
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

class CmdService {
    private val options = Options()

    fun parse(args: Array<String>): Cmd {
        val clp: CommandLineParser = DefaultParser()

        options.addOption(Option("login", true, "Login"))
        options.addOption(Option("pass", true, "Pass"))
        options.addOption(Option("h", false, "Help"))

        options.addOption(Option("res", true, "Level"))
        options.addOption(Option("role", true, "Role"))

        options.addOption(Option("ds", true, "Start date"))
        options.addOption(Option("de", true, "End date"))
        options.addOption(Option("vol", true, "Value"))

        var login = ""
        var pass = ""
        var help: Boolean

        var res: String? = null
        var role: String? = null

        var ds: LocalDate? = null
        var de: LocalDate? = null
        var vol: Int? = null

        val cl: CommandLine
        //over hard logic!!!
        try {
            cl = clp.parse(options, args)

            help = cl.hasOption("help")
            login = cl.getOptionValue("login")
            pass = cl.getOptionValue("pass")

            if (cl.hasOption("res") && cl.hasOption("role")) {
                res = cl.getOptionValue("res")
                role = cl.getOptionValue("role")

                if (cl.hasOption("ds") && cl.hasOption("de") && cl.hasOption("vol")) {
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

                    ds = LocalDate.parse(cl.getOptionValue("ds"), formatter)
                    de = LocalDate.parse(cl.getOptionValue("de"), formatter)
                    vol = cl.getOptionValue("vol").toInt()
                }
            }

        } catch (ex: Exception) {
            help = true
        }

        return Cmd(login, pass, res, role, ds, de, vol, help)
    }

    fun help() {
        HelpFormatter().printHelp("aaa", options, true)
    }

    inline fun <reified T : Enum<T>> checkRole(role: String): Boolean {
        return enumValues<T>().any { it.name == role }
    }

    fun checkNode(res: String): Boolean {
        val nodeRegex = Pattern.compile(
            "[a-zA-Z]{1,10}",
            Pattern.CASE_INSENSITIVE
        )

        val matcher: Matcher = nodeRegex.matcher(res)
        return matcher.find()
    }
}