package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager.getLogger
import ru.danil42russia.aaa.domain.ExitCode
import ru.danil42russia.aaa.domain.Roles
import ru.danil42russia.aaa.domain.User

class BusinessLogic {
    private val log = getLogger(BusinessLogic::class.java)

    /**
     * Authenticates user
     *
     * @param login login entered
     * @param pass password entered
     * @param help
     * @param users list of users
     * @param cmdService
     * @param userService
     *
     * @return SUCCESS if everything is us, HELP if a wrong argument or need to get help, BAD_LOGIN_FORMAT if login format is incorrect, BAD_LOGIN if the user is not found, BAD_PASSWORD if the password is incorrect
     */
    fun authentication(
        login: String,
        pass: String,
        help: Boolean,
        users: List<User>,
        cmdService: CmdService,
        userService: UserService
    ): ExitCode {
        var isEditCode = false //Используется для предотвращение изменения exitCodes
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (help) {
            log.debug("Print help")
            cmdService.help()
            exitCodes = ExitCode.HELP
            isEditCode = true
        }

        if (!userService.checkLogin(login) && !isEditCode) {
            log.debug("Incorrect login format")
            exitCodes = ExitCode.BAD_LOGIN_FORMAT
            isEditCode = true
        }

        val user = userService.findUserByLogin(login, users)
        if (user != null) {
            val hashPassword = userService.encrypt(pass, user.salt)

            if (!userService.validatePass(user, hashPassword) && !isEditCode) {
                log.debug("Wrong password")
                exitCodes = ExitCode.BAD_PASSWORD
            }
        } else {
            if (!isEditCode) {
                log.debug("Wrong login")
                exitCodes = ExitCode.BAD_LOGIN
            }
        }

        return exitCodes
    }

    /**
     * Authorizes user
     *
     * @param role role entered
     * @param cmdService
     *
     * @return SUCCESS if everything is us, BAD_ROLE if not the right role
     */
    fun authorization(role: String?, cmdService: CmdService): ExitCode {
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (role != null) {
            if (!cmdService.checkRole<Roles>(role)) {
                log.debug("Wrong role")
                exitCodes = ExitCode.BAD_ROLE
            }
        }
        //TODO add NOT_PERMISSION
        return exitCodes
    }

    /**
     * Accounts user
     *
     * @return SUCCESS if everything is us
     */
    fun accounting(): ExitCode {
        //TODO add INCORRECT_ACTIVITY
        return ExitCode.SUCCESS
    }
}