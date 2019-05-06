package ru.danil42russia.aaa.service

import org.apache.commons.cli.*
import ru.danil42russia.aaa.domain.Cmd
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CmdService {
    private val options = Options()

    init {
        options.addOption(Option("login", true, "Login"))
        options.addOption(Option("pass", true, "Pass"))
        options.addOption(Option("h", false, "Help"))

        options.addOption(Option("res", true, "Level"))
        options.addOption(Option("role", true, "Role"))

        options.addOption(Option("ds", true, "Start date"))
        options.addOption(Option("de", true, "End date"))
        options.addOption(Option("vol", true, "Value"))
    }

    fun parse(args: Array<String>): Cmd {
        val clp: CommandLineParser = DefaultParser()

        var login = ""
        var pass = ""
        var help: Boolean

        var res: String? = null
        var role: String? = null

        var ds: LocalDate? = null
        var de: LocalDate? = null
        var vol: Int? = null

        val cl: CommandLine

        try {
            cl = clp.parse(options, args)

            help = cl.hasOption("help")
            login = cl.getOptionValue("login")
            pass = cl.getOptionValue("pass")

            if (isAuthorization(cl)) {
                res = cl.getOptionValue("res")
                role = cl.getOptionValue("role")
            }

            if (isAccounting(cl)) {
                ds = parseData(cl.getOptionValue("ds"))
                de = parseData(cl.getOptionValue("de"))
                vol = cl.getOptionValue("vol").toInt()
            }

        } catch (ex: Exception) {
            help = true
        }

        return Cmd(login, pass, res, role, ds, de, vol, help)
    }

    fun help() {
        HelpFormatter().printHelp("aaa", options, true)
    }

    private fun isAuthorization(cl: CommandLine) =
        cl.hasOption("res") && cl.hasOption("role")

    private fun isAccounting(cl: CommandLine) =
        isAuthorization(cl) && cl.hasOption("ds") && cl.hasOption("de") && cl.hasOption("vol")

    private fun parseData(text: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        return LocalDate.parse(text, formatter)
    }

    inline fun <reified T : Enum<T>> checkRole(role: String): Boolean {
        return enumValues<T>().any { it.name == role }
    }
}