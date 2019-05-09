package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import java.sql.Connection
import java.sql.DriverManager

class DBService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private var connection: Connection? = null

    init {
        log.debug("Connection start")
        Class.forName("org.sqlite.JDBC")

        try {
            connection = DriverManager.getConnection(
                System.getenv("DB-URL"),
                System.getenv("DB-LOGIN"),
                System.getenv("DB-PASSWORD")
            )
            log.debug("Connection successful")
        } catch (ex: Exception) {
            log.debug("Connection failed $ex")
            connection = null
        }

    }

    fun getConnection(): Connection? {
        return connection
    }

    fun close() {
        connection?.close()
    }
}