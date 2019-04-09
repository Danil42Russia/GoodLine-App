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

    fun authentication(cmd: Cmd, users: ArrayList<User>): ExitCodes {
        val userService = UserService()
        var isEditCode = false
        var exitCodes: ExitCodes = ExitCodes.SUCCESS

        val user: User?
        val login: String = cmd.login
        val password: String = cmd.pass
        val hashPassword: String

        if (!userService.checkLogin(login) && !isEditCode) {
            exitCodes = ExitCodes.BADLOGINFORMAT
            isEditCode = true
        }

        user = userService.findUserByLogin(login, users)
        if (user != null) {
            hashPassword = userService.encrypt(password, user.salt)

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