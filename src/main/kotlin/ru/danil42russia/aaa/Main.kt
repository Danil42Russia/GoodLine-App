package ru.danil42russia.aaa

import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.service.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cmdService = CmdService()
    val businessLogic = BusinessLogic()
    val migrationService = MigrationService()
    val dbService = DBService()

    migrationService.migrate()
    val connection = dbService.getConnection()
    var exitCodes = ExitCode.NO_AUTH

    if (connection != null) {
        val userService = UserService(connection)
        val cmd = cmdService.parse(args)

        exitCodes = businessLogic.authentication(cmd.login, cmd.pass, cmd.help, cmdService, userService)

        if (exitCodes == ExitCode.SUCCESS) {
            exitCodes = businessLogic.authorization(cmd.role, userService)
        }

        if (exitCodes == ExitCode.SUCCESS) {
            exitCodes = businessLogic.accounting(cmd.ds, cmd.de, cmd.vol, userService)
        }
    }

    dbService.close()
    exitProcess(exitCodes.code)
}