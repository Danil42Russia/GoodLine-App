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
        val sql = "SELECT id, pass, salt FROM users WHERE login = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, login)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        var id = -1
        var pass = ""
        var salt = ""

        if (resultSet.next()) {
            id = resultSet.getInt(1)
            pass = resultSet.getString(2)
            salt = resultSet.getString(3)
        }

        resultSet.close()
        preparedStatement.close()

        return when {
            id == -1 || pass == "" || salt == "" -> null
            else -> User(id, login, pass, salt)
        }
    }

    fun getAllUsers(): List<User> {

        val sql = "SELECT id, login FROM users"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        val list = mutableListOf<User>()

        while (resultSet.next()) {
            list.add(User(resultSet.getInt(1), resultSet.getString(2)))
        }

        resultSet.close()
        preparedStatement.close()

        return list
    }
}