package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.ExitCode
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private var connection: Connection? = null

    init {
        log.debug("Connection start")
        Class.forName("org.sqlite.JDBC")

        try {
            connection = DriverManager.getConnection(
                System.getenv("DBURL"),
                System.getenv("DBLOGIN"),
                System.getenv("DBPASSWORD")
            )
            log.debug("Connection successful")
        } catch (ex: SQLException) {
            log.debug("Connection failed $ex")
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

    fun isOpen(): ExitCode {
        return when (connection) {
            null -> ExitCode.OTHER
            else -> ExitCode.SUCCESS
        }
    }

    fun close() {
        connection?.close()
        log.debug("Connection close")
    }
}