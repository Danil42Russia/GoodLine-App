package ru.danil42russia.aaa.service

import ru.danil42russia.aaa.domain.ExitCodes
import ru.danil42russia.aaa.domain.Roles
import ru.danil42russia.aaa.domain.User

class BusinessLogic {
    //0,1,2
    fun authentication(login: String, help: Boolean, cmd: CmdService): ExitCodes {
        val userService = UserService()
        var isEditCode = false
        var exitCodes: ExitCodes = ExitCodes.SUCCESS

        if (help) {
            cmd.help()
            exitCodes = ExitCodes.HELP
            isEditCode = true
        }

        if (!userService.checkLogin(login) && !isEditCode) {
            exitCodes = ExitCodes.BADLOGINFORMAT
        }

        return exitCodes
    }

    //0,3,4
    fun authorization(login: String, pass: String, users: List<User>): ExitCodes {
        val userService = UserService()
        var exitCodes: ExitCodes = ExitCodes.SUCCESS

        val user = userService.findUserByLogin(login, users)
        if (user != null) {
            val hashPassword = userService.encrypt(pass, user.salt)

            if (!userService.validatePass(user, hashPassword)) {
                exitCodes = ExitCodes.BADPASSWORD
            }
        } else {
            exitCodes = ExitCodes.BADLOGIN
        }

        return exitCodes
    }

    //0,5
    fun accounting(role: String?, cmd: CmdService): ExitCodes {
        var exitCodes: ExitCodes = ExitCodes.SUCCESS

        if (role != null) {
            if (!cmd.checkRole<Roles>(role))
                exitCodes = ExitCodes.BADROLE
        }
        return exitCodes
    }
}