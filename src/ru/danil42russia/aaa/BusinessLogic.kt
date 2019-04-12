package ru.danil42russia.aaa

class BusinessLogic {
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