package ru.danil42russia.aaa.service

import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.domain.Roles
import ru.danil42russia.aaa.domain.User

class BusinessLogic {
    //0,1,2
    fun authentication(login: String, help: Boolean, cmd: CmdService): ExitCode {
        val userService = UserService()
        var isEditCode = false
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (help) {
            cmd.help()
            exitCodes = ExitCode.HELP
            isEditCode = true
        }

        if (!userService.checkLogin(login) && !isEditCode) {
            exitCodes = ExitCode.BADLOGINFORMAT
        }

        return exitCodes
    }

    //0,3,4
    fun authorization(login: String, pass: String, users: List<User>): ExitCode {
        val userService = UserService()
        var exitCodes: ExitCode = ExitCode.SUCCESS

        val user = userService.findUserByLogin(login, users)
        if (user != null) {
            val hashPassword = userService.encrypt(pass, user.salt)

            if (!userService.validatePass(user, hashPassword)) {
                exitCodes = ExitCode.BADPASSWORD
            }
        } else {
            exitCodes = ExitCode.BADLOGIN
        }

        return exitCodes
    }

    //0,5
    fun accounting(role: String?, cmd: CmdService): ExitCode {
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (role != null) {
            if (!cmd.checkRole<Roles>(role))
                exitCodes = ExitCode.BADROLE
        }
        return exitCodes
    }
}