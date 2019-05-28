package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import java.sql.Connection
import java.sql.Statement

class AccountingDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)

    fun addActivity(
        login: String,
        roleID: Int,
        res: String,
        ds: String,
        de: String,
        vol: Int
    ) {
        log.debug("add activity data to DB")

        val sqlInsertUS =
            "INSERT INTO users_roles(id_user, id_role, res) VALUES ((SELECT id FROM users WHERE login = ?), ?, ?)"

        var genID: Int? = null

        connection.prepareStatement(sqlInsertUS, Statement.RETURN_GENERATED_KEYS).use { ps ->
            ps.setString(1, login)
            ps.setInt(2, roleID)
            ps.setString(3, res)
            ps.executeUpdate()
            ps.generatedKeys.use { rs ->
                if (rs.next()) {
                    genID = rs.getInt(1)
                }
            }
        }

        if (genID != null) {
            val sqlInsertActivity = "INSERT INTO activity(id_ur, ds, de, vol) VALUES (?, ?, ?, ?)"

            connection.prepareStatement(sqlInsertActivity).use { ps ->
                ps.setInt(1, genID!!)
                ps.setString(2, ds)
                ps.setString(3, de)
                ps.setInt(4, vol)
                ps.executeUpdate()
            }
        }
    }
}