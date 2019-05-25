package ru.danil42russia.aaa

import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.domain.enums.ExitCode
import ru.danil42russia.aaa.service.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    exitProcess(application(args))
}

fun application(args: Array<String>): Int {
    var exitCodes: ExitCode

    val migrationService = MigrationService()
    migrationService.migrate()

    val dbService = DBService()
    val connection = dbService.getConnection()

    val cmdService = CmdService()
    val cmd = cmdService.parse(args)

    val userService = UserService()

    val businessLogic = BusinessLogic(cmdService, userService)

    if (connection != null) {
        val authenticationDao = AuthenticationDao(connection)

        exitCodes = businessLogic.authentication(cmd.login, cmd.pass, cmd.help, authenticationDao)

        if (exitCodes == ExitCode.SUCCESS && cmdService.isAuthorization) {
            exitCodes = businessLogic.authorization(cmd.role, userService)
        }

        if (exitCodes == ExitCode.SUCCESS && cmdService.isAccounting) {
            exitCodes = businessLogic.accounting(cmd.ds, cmd.de, cmd.vol)
        }
    } else {
        exitCodes = ExitCode.OTHER
    }

    dbService.close()
    return exitCodes.code
}