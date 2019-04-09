package ru.danil42russia.aaa

class CmdService {
    fun parse(args: Array<String>): Cmd {
        var login = ""
        var password = ""
        var help = false
        var i = 0

        while (i < args.size) {
            if (args[i] == "-login") {
                i++
                login = args[i]
            }
            if (args[i] == "-pass") {
                i++
                password = args[i]
            }
            i++
        }

        if (password.isEmpty() || login.isEmpty())
            help = true

        i = 0
        while (i < args.size) {
            if ((args[i] != "-login" && args[i] != "-pass" && args[i] != login && args[i] != password)) {
                help = true
                break
            }
            i++
        }

        return Cmd(login, password, help)
    }

    fun help() {
        println("using: aaa [-login] <login> [-pass] <password> [-h]")
    }
}