package ru.danil42russia.aaa

import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.dao.AuthorizationDao
import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.service.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var exitCodes: ExitCode

    val migrationService = MigrationService()
    migrationService.migrate()

    val dbService = DBService()
    val connection = dbService.getConnection()

    val cmdService = CmdService()
    val cmd = cmdService.parse(args)

    val userService = UserService()

    val businessLogic = BusinessLogic(cmdService, userService)

    exitCodes = dbService.isOpen()
    if (exitCodes == ExitCode.SUCCESS) {
        val authenticationDao = AuthenticationDao(connection!!)
        val authorizationDao = AuthorizationDao(connection)

        exitCodes = businessLogic.authentication(cmd.login, cmd.pass, cmd.help, authenticationDao)

        if (exitCodes == ExitCode.SUCCESS && cmdService.isAuthorization) {
            exitCodes = businessLogic.authorization(cmd.role, authorizationDao)
        }

        if (exitCodes == ExitCode.SUCCESS && cmdService.isAccounting) {
            exitCodes = businessLogic.accounting(cmd.ds, cmd.de, cmd.vol)
        }
    }

    dbService.close()
    exitProcess(exitCodes.code)
}