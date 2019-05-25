package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager.getLogger
import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.domain.ExitCode

class BusinessLogic(private val cmdService: CmdService, private val userService: UserService) {
    private val log = getLogger(BusinessLogic::class.java)

    /**
     * Authenticates user
     *
     * @param login login entered
     * @param pass password entered
     * @param help
     *
     * @return SUCCESS if everything is us, HELP if a wrong argument or need to get help, BAD_LOGIN_FORMAT if login format is incorrect, BAD_LOGIN if the user is not found, BAD_PASSWORD if the password is incorrect
     */
    fun authentication(
        login: String,
        pass: String,
        help: Boolean,
        authenticationDao: AuthenticationDao
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

        val user = authenticationDao.findUserByLogin(login)
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
     *
     * @return SUCCESS if everything is us, BAD_ROLE if not the right role
     */
    fun authorization(role: String, userService: UserService): ExitCode {
        var exitCodes: ExitCode = ExitCode.SUCCESS

        if (!userService.checkRole(role)) {
            log.debug("Wrong role")
            exitCodes = ExitCode.BAD_ROLE
        }
        //TODO add NOT_PERMISSION
        return exitCodes
    }

    /**
     * Accounts user
     *
     * @return SUCCESS if everything is us, INCORRECT_ACTIVITY if an invalid date or amount
     */
    fun accounting(ds: String, de: String, vol: String): ExitCode {
        var isEditCode = false //Используется для предотвращение изменения exitCodes
        var exitCodes: ExitCode = ExitCode.SUCCESS

        val parseDs = userService.parseData(ds)
        val parseDe = userService.parseData(de)

        if (parseDs == null || parseDe == null) {
            exitCodes = ExitCode.INVALID_DATE
            isEditCode = true
        } else {
            if (!userService.checkDate(parseDs, parseDe)) {
                exitCodes = ExitCode.INVALID_DATE
                isEditCode = true
            }
        }

        if (!isEditCode) {
            val parseVol = userService.parseVolume(vol)
            if (parseVol == null || !userService.checkVolume(parseVol)) {
                exitCodes = ExitCode.INVALID_VOLUME
            }
        }

        return exitCodes
    }
}