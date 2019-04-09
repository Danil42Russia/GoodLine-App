package ru.danil42russia.aaa

import java.util.regex.Matcher
import java.util.regex.Pattern

class UserService {
    private val businessLogic: BusinessLogic = BusinessLogic()
    private val emailRegex = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
        Pattern.CASE_INSENSITIVE
    )

    fun checkLogin(login: String): Boolean {
        val matcher: Matcher = emailRegex.matcher(login)
        return matcher.find()
    }

    fun findUserByLogin(login: String, user: ArrayList<User>): User? {
        return user.find { it.login == login }
    }

    fun encrypt(pass: String, salt: String): String {
        return businessLogic.SHA256(businessLogic.SHA256(pass) + salt)
    }

    fun validatePass(user: User, pass: String): Boolean{
        return  user.pass == pass
    }
}