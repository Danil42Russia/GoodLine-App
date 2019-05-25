package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.User
import ru.danil42russia.aaa.domain.enums.Roles
import ru.danil42russia.aaa.utils.sha256
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private val emailRegex = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
        Pattern.CASE_INSENSITIVE
    )

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

    fun checkRole(role: String): Boolean {
        log.debug("Find role")

        val roles = Roles.values().find { it.name == role }

        return roles != null
    }

    fun getRoleID(role: String): Int {
        return Roles.values().find { it.name == role }?.code!!
    }

    fun parseDate(text: String): LocalDate? {
        log.debug("Parse date")
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        var date: LocalDate?
        try {
            log.debug("Date parse successful")
            date = LocalDate.parse(text, formatter)
        } catch (ex: DateTimeParseException) {
            log.error("Date parse failed $ex")
            date = null
        }

        return date
    }

    fun checkDate(ds: LocalDate, de: LocalDate): Boolean {
        log.debug("Check date difference")

        return when {
            ds < de -> {
                log.debug("Check date difference successful")
                true
            }
            else -> {
                log.debug("Check date difference failed")
                false
            }
        }
    }

    fun parseVolume(vol: String): Int? {
        log.debug("Date volume")

        var volume: Int?
        try {
            log.debug("Parse volume successful")
            volume = vol.toInt()
        } catch (ex: NumberFormatException) {
            log.error("Parse volume failed $ex")
            volume = null
        }

        return volume
    }

    fun checkVolume(vol: Int): Boolean {
        log.debug("Check volume difference")

        return when {
            vol > 0 -> {
                log.debug("Check volume difference successful")
                true
            }
            else -> {
                log.debug("Check volume difference failed")
                false
            }
        }
    }
}