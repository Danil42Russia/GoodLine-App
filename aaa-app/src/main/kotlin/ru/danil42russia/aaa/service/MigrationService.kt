package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.FlywayException

class MigrationService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private val flyway: Flyway = Flyway()

    init {
        val dbUrl = System.getenv("DB_URL")
        val dbLogin = System.getenv("DB_LOGIN")
        val dbPass = System.getenv("DB_PASSWORD")

        log.debug("DB data url: $dbUrl, login: $dbLogin, pass: $dbPass")
        flyway.setDataSource(dbUrl, dbLogin, dbPass)
    }

    fun migrate() {
        log.debug("Start migration")

        try {
            flyway.migrate()
            log.debug("Migration successful")
        } catch (ex: FlywayException) {
            log.error("Migration failed $ex")
        }
    }
}