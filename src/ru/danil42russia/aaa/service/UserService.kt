package ru.danil42russia.aaa.service

import ru.danil42russia.aaa.domain.User
import ru.danil42russia.aaa.utils.sha256
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserService {
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
        val matcher: Matcher = emailRegex.matcher(login)
        return matcher.find()
    }

    /**
     * Looking for a user by login
     *
     * @param login login entered
     * @param users list of users
     *
     * @return User
     */
    fun findUserByLogin(login: String, users: List<User>): User? {
        return users.find { it.login == login }
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
        return user.pass == pass
    }
}