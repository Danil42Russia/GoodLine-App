package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import java.sql.Connection

class AuthorizationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)
}