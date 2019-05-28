package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private var connection: Connection? = null

    init {
        log.debug("Connection start")
        val dbClass = System.getenv("DB_CLASS")
        val dbUrl = System.getenv("DB_URL")
        val dbLogin = System.getenv("DB_LOGIN")
        val dbPass = System.getenv("DB_PASSWORD")

        log.debug("DB Connection data -> class: $dbClass url: $dbUrl, login: $dbLogin, pass: $dbPass")

        Class.forName(dbClass)
        try {
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPass)
            log.debug("Connection successful")
        } catch (ex: SQLException) {
            log.error("Connection failed $ex")
            connection = null
        } finally {
            if (connection == null) {
                close()
            }
        }

    }

    fun getConnection(): Connection? {
        return connection
    }

    fun close() {
        connection?.close()
        log.debug("Connection close")
    }
}