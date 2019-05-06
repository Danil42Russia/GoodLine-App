package ru.danil42russia.aaa

import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.domain.User
import ru.danil42russia.aaa.service.BusinessLogic
import ru.danil42russia.aaa.service.CmdService
import ru.danil42russia.aaa.service.UserService
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cmdService = CmdService()
    val businessLogic = BusinessLogic()
    val userService = UserService()

    val users = listOf(
        User(
            "user@xyz.com",
            "605b05f6fcc4eb9013a58287ec82ba935aa81b7f2a747404165caf129384d1e7",
            "b742f2a1ad171e30b1d36af0c0226cc7b003b3e0c4673586cb8b07602f05ed82"
        ), User(
            "user@mk.ru",
            "a56a0e11e5cb4f0cf61ba2c6181556efb452928dfa899e4390bb44c406f98a8b",
            "807d16ef77c55e79fa210d50006093343a709304c8762a4f5c22e03364301369"
        )

    )

    val cmd = cmdService.parse(args)

    var exitCodes = businessLogic.authentication(cmd.login, cmd.pass, cmd.help, users, cmdService, userService)

    if (exitCodes == ExitCode.SUCCESS) {
        exitCodes = businessLogic.authorization(cmd.role, cmdService)
    }

    if (exitCodes == ExitCode.SUCCESS) {
        exitCodes = businessLogic.accounting()
    }

    exitProcess(exitCodes.code)
}