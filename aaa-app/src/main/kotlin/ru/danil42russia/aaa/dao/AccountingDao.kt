package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AccountingDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    fun add(
        login: String,
        res: String,
        role: String,
        ds: LocalDate,
        de: LocalDate,
        vol: Int
    ) {
        log.debug("add data")
        val query =
            "INSERT INTO accounting (login, res, roles, dataStart, dataEnd, volume) VALUES (?, ?, ?,?, ?, ?)"
        connection.prepareStatement(query, 1).use { ps ->
            ps.setString(1, login)
            ps.setString(2, res)
            ps.setString(3, role)
            ps.setString(4, ds.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            ps.setString(5, de.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            ps.setInt(6, vol)
            ps.executeUpdate()
        }
    }
}