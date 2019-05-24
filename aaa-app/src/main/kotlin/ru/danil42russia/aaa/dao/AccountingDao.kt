package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection

class AccountingDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    fun addActivity(
        login: String,
        res: String,
        role: String,
        ds: String,
        de: String,
        vol: String
    ) {
        log.debug("add activity data to DB")
        val query =
            "INSERT INTO activity (id_user, res, roles, dataStart, dataEnd, volume) VALUES ((SELECT id FROM users WHERE login = ?), ?, ?, ?, ?, ?)"
        connection.prepareStatement(query).use { ps ->
            ps.setString(1, login)
            ps.setString(2, res)
            ps.setString(3, role)
            ps.setString(4, ds)
            ps.setString(5, de)
            ps.setInt(6, vol.toInt())
            ps.executeUpdate()
        }
    }
}