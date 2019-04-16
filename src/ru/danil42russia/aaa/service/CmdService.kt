package ru.danil42russia.aaa.service

import org.apache.commons.cli.*
import ru.danil42russia.aaa.domain.Cmd

class CmdService {
    fun parse(args: Array<String>): Cmd {
        val clp: CommandLineParser = DefaultParser()
        val options = Options()
        options.addOption(Option("login", true, "Login"))
        options.addOption(Option("pass", true, "Pass"))
        options.addOption(Option("h", false, "Help"))

        val cl: CommandLine = clp.parse(options, args)

        return Cmd(cl.getOptionValue("login"), cl.getOptionValue("pass"), cl.hasOption("h"))
    }

    fun help() {
        println("using: aaa [-login] <login> [-pass] <password> [-h]")
    }
}