package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection

class AuthorizationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    fun checkRole(role: String): Boolean {
        log.debug("Find role in the DB")
        val sql = "SELECT name FROM roles WHERE name = ?"

        var resultRole = ""

        connection.prepareStatement(sql).use { ps ->
            ps.setString(1, role)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    resultRole = rs.getString(1)
                }
            }
        }

        return resultRole == role
    }
}