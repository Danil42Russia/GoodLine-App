package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.User
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class AuthenticationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    /**
     * Looking for a user by login
     *
     * @param login login entered
     *
     * @return User
     */
    fun findUserByLogin(login: String): User? {
        log.debug("Find user by login")
        val sql = "SELECT pass, salt FROM users WHERE login = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, login)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        var pass = ""
        var salt = ""

        if (resultSet.next()) {
            pass = resultSet.getString(1)
            salt = resultSet.getString(2)
        }

        resultSet.close()
        preparedStatement.close()

        return when {
            pass == "" || salt == "" -> null
            else -> User(login, pass, salt)
        }
    }
}