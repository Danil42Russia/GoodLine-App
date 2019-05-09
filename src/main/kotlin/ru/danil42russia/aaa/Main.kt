package ru.danil42russia.aaa

import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.dao.AuthorizationDao
import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.service.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cmdService = CmdService()
    val userService = UserService()
    val migrationService = MigrationService()
    val dbService = DBService()

    migrationService.migrate()
    val connection = dbService.getConnection()
    var exitCodes = ExitCode.NO_AUTH

    if (connection != null) {
        val businessLogic = BusinessLogic(cmdService, userService)

        val authenticationDao = AuthenticationDao(connection)
        val authorizationDao = AuthorizationDao(connection)

        val cmd = cmdService.parse(args)

        exitCodes = businessLogic.authentication(cmd.login, cmd.pass, cmd.help, authenticationDao)

        if (exitCodes == ExitCode.SUCCESS) {
            exitCodes = businessLogic.authorization(cmd.role, authorizationDao)
        }

        if (exitCodes == ExitCode.SUCCESS) {
            exitCodes = businessLogic.accounting(cmd.ds, cmd.de, cmd.vol)
        }
    }

    dbService.close()
    exitProcess(exitCodes.code)
}