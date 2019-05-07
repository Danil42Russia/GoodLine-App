package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.dao.AuthenticationDao
import ru.danil42russia.aaa.dao.AuthorizationDao
import ru.danil42russia.aaa.domain.User
import ru.danil42russia.aaa.utils.sha256
import java.sql.Connection
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserService(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private val emailRegex = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
        Pattern.CASE_INSENSITIVE
    )

    private val authenticationDao = AuthenticationDao(connection)
    private val authorizationDao = AuthorizationDao(connection)

    /**
     * Checks login for validity
     *
     * @param login
     *
     * @return true if the login is valid
     */
    fun checkLogin(login: String): Boolean {
        log.debug("Check login with regular expression")
        val matcher: Matcher = emailRegex.matcher(login)
        return matcher.find()
    }

    /**
     * Hashes password using SHA-256 algorithm
     *
     * @param pass password entered
     * @param salt salt
     *
     * @return hashed password
     */
    fun encrypt(pass: String, salt: String): String {
        log.debug("Encrypt password")
        return sha256(sha256(pass) + salt)
    }

    /**
     * Looking for a user by login
     *
     * @param login login entered
     *
     * @return User
     */
    fun findUserByLogin(login: String): User? =
        authenticationDao.findUserByLogin(login)

    /**
     * Compare password
     *
     * @param user user instance
     * @param pass hashed password
     *
     * @return true if the password matched
     */
    fun validatePass(user: User, pass: String): Boolean {
        log.debug("Validate password")
        return user.pass == pass
    }

    fun checkRole(role: String): Boolean =
        authorizationDao.checkRole(role)
}