package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AuthorizationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    fun checkRole(role: String): Boolean {
        log.debug("Find role in the DB")
        val sql = "SELECT name FROM roles WHERE name = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, role)
        val resultSet: ResultSet = preparedStatement.executeQuery()

        var resultRole = ""

        if (resultSet.next()) {
            resultRole = resultSet.getString(1)
        }

        return resultRole == role
    }
}