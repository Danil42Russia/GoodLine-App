package ru.danil42russia.aaa.service

import org.apache.commons.cli.*
import ru.danil42russia.aaa.domain.Cmd

class CmdService {
    private val options = Options()
    fun parse(args: Array<String>): Cmd {
        val clp: CommandLineParser = DefaultParser()
        var help: Boolean

        options.addOption(Option("login", true, "Login"))
        options.addOption(Option("pass", true, "Pass"))
        options.addOption(Option("h", false, "Help"))

        options.addOption(Option("res", false, "Level"))
        options.addOption(Option("role", false, "Role"))
        options.addOption(Option("ds", false, "Start date"))
        options.addOption(Option("de", false, "End date"))
        options.addOption(Option("vol", false, "Value"))

        var login = ""
        var pass = ""

        val cl: CommandLine
        try {
            cl = clp.parse(options, args)

            help = cl.hasOption("help")
            login = cl.getOptionValue("login")
            pass = cl.getOptionValue("pass")
        } catch (ex: Exception) {
            help = true
        }

        return Cmd(login, pass, help)
    }

    fun help() {
        val formatter = HelpFormatter()
        formatter.printHelp("aaa", options, true)
    }
}