package ru.danil42russia.aaa.service

import org.apache.commons.cli.*
import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.Cmd
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CmdService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private val options = Options()

    var isAuthorization: Boolean = false
        private set
    var isAccounting: Boolean = false
        private set

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
        log.debug("Parse start")
        val clp: CommandLineParser = DefaultParser()

        var login = ""
        var pass = ""
        var help: Boolean

        var res = ""
        var role = ""

        //TODO изменить на String
        var ds: LocalDate = LocalDate.now()
        var de: LocalDate = LocalDate.now()
        var vol = 0

        val cl: CommandLine

        try {
            cl = clp.parse(options, args)

            help = cl.hasOption("help")
            login = cl.getOptionValue("login")
            pass = cl.getOptionValue("pass")

            if (isAuthorization(cl)) {
                res = cl.getOptionValue("res")
                role = cl.getOptionValue("role")
                isAuthorization = true
            }

            if (isAccounting(cl)) {
                ds = parseData(cl.getOptionValue("ds"))
                de = parseData(cl.getOptionValue("de"))
                vol = cl.getOptionValue("vol").toInt()
                isAccounting = true
            }

            log.debug("Parse successful")
        } catch (ex: Exception) {
            log.error("Parse failed $ex")
            help = true
        }

        return Cmd(login, pass, res, role, ds, de, vol, help)
    }

    fun help() {
        log.debug("Print help")
        HelpFormatter().printHelp("aaa", options, true)
    }

    private fun isAuthorization(cl: CommandLine) =
        cl.hasOption("res") && cl.hasOption("role")

    private fun isAccounting(cl: CommandLine) =
        isAuthorization(cl) && cl.hasOption("ds") && cl.hasOption("de") && cl.hasOption("vol")

    private fun parseData(text: String): LocalDate {
        log.debug("Parse date")
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        return LocalDate.parse(text, formatter)
    }
}