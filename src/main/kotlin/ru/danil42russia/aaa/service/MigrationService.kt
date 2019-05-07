package ru.danil42russia.aaa.service

import org.flywaydb.core.Flyway

class MigrationService {
    private val flyway: Flyway = Flyway()

    init {
        flyway.setDataSource("jdbc:sqlite:aaa.db", "", "")
    }

    fun migrate() {
        flyway.migrate()
    }
}