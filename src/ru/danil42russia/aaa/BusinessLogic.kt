package ru.danil42russia.aaa

import java.math.BigInteger
import java.security.MessageDigest

class BusinessLogic {
    fun SHA256(text: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val messageDigest = md.digest(text.toByteArray())
        val no = BigInteger(1, messageDigest)
        var hashText = no.toString(16)
        while (hashText.length < 32) {
            hashText = "0$hashText"
        }

        return hashText
    }

    fun authentication(login: String, pass: String, users: List<User>): ExitCodes {
        val userService = UserService()
        var isEditCode = false
        var exitCodes: ExitCodes = ExitCodes.SUCCESS

        if (!userService.checkLogin(login) && !isEditCode) {
            exitCodes = ExitCodes.BADLOGINFORMAT
            isEditCode = true
        }

        val user = userService.findUserByLogin(login, users)
        if (user != null) {
            val hashPassword = userService.encrypt(pass, user.salt)

            if (!userService.validatePass(user, hashPassword) && !isEditCode) {
                exitCodes = ExitCodes.BADPASSWORD
            }
        } else {
            if (!isEditCode)
                exitCodes = ExitCodes.BADLOGIN
        }

        return exitCodes
    }
}