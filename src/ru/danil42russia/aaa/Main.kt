package ru.danil42russia.aaa

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val cmdService = CmdService()
    val userService = UserService()

    var exitCodes = ExitCodes.SUCCESS
    var isEditCode = false

    val user: User?
    val login: String
    val password: String
    val hashPassword: String

    val users = ArrayList<User>()
    users.add(
        User(
            "user@xyz.com",
            "605b05f6fcc4eb9013a58287ec82ba935aa81b7f2a747404165caf129384d1e7",
            "b742f2a1ad171e30b1d36af0c0226cc7b003b3e0c4673586cb8b07602f05ed82"
        )
    )
    users.add(
        User(
            "user@mk.ru",
            "a56a0e11e5cb4f0cf61ba2c6181556efb452928dfa899e4390bb44c406f98a8b",
            "807d16ef77c55e79fa210d50006093343a709304c8762a4f5c22e03364301369"
        )
    )

    val cmd = cmdService.parse(args)
    login = cmd.login
    password = cmd.pass

    if (cmd.help) {
        cmdService.help()
        exitCodes = ExitCodes.HELP
        isEditCode = true
    }
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

    exitProcess(exitCodes.ordinal)
}