package ru.danil42russia.aaa.service

import org.apache.logging.log4j.LogManager
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.FlywayException

class MigrationService {
    private val log = LogManager.getLogger(BusinessLogic::class.java)
    private val flyway: Flyway = Flyway()

    init {
        flyway.setDataSource(
            System.getenv("DB_URL"),
            System.getenv("DB_LOGIN"),
            System.getenv("DB_PASSWORD")
        )
        flyway.setLocations("filesystem:src/main/resources/db/migration")
    }

    fun migrate() {
        log.debug("Start migration")

        try {
            flyway.migrate()
            log.debug("Migration successful")
        } catch (ex: FlywayException) {
            log.debug("Migration failed $ex")
        }
    }
}