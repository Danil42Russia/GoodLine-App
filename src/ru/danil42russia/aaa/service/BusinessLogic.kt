package ru.danil42russia.aaa.service

import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.domain.Roles
import ru.danil42russia.aaa.domain.User

class BusinessLogic {

    /**
     * Authenticates user
     *
     * @param login login entered
     * @param help
     * @param cmdService
     * @param userService
     *
     * @return SUCCESS if everything is us, HELP if a wrong argument or need to get help, BAD_LOGIN_FORMAT if login format is incorrect
     */
    fun authentication(login: String, help: Boolean, cmdService: CmdService, userService: UserService): ExitCode {
        var isEditCode = false //Используется для предотвращение изменения exitCodes
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (help) {
            cmdService.help()
            exitCodes = ExitCode.HELP
            isEditCode = true
        }

        if (!userService.checkLogin(login) && !isEditCode) {
            exitCodes = ExitCode.BAD_LOGIN_FORMAT
        }

        return exitCodes
    }

    /**
     * Authorizes user
     *
     * @param login login entered
     * @param pass password entered
     * @param userService
     * @param users list of users
     *
     * @return SUCCESS if everything is us, BAD_LOGIN if the user is not found, BAD_PASSWORD if the password is incorrect
     */
    fun authorization(login: String, pass: String, userService: UserService, users: List<User>): ExitCode {
        var exitCodes: ExitCode = ExitCode.SUCCESS

        val user = userService.findUserByLogin(login, users)
        if (user != null) {
            val hashPassword = userService.encrypt(pass, user.salt)

            if (!userService.validatePass(user, hashPassword)) {
                exitCodes = ExitCode.BAD_PASSWORD
            }
        } else {
            exitCodes = ExitCode.BAD_LOGIN
        }

        return exitCodes
    }

    /**
     * Accounts user
     *
     * @param role role entered
     * @param cmdService
     *
     * @return SUCCESS if everything is us, BAD_ROLE if not the right role
     */
    fun accounting(role: String?, cmdService: CmdService): ExitCode {
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (role != null) {
            if (!cmdService.checkRole<Roles>(role))
                exitCodes = ExitCode.BAD_ROLE
        }
        return exitCodes
    }
}