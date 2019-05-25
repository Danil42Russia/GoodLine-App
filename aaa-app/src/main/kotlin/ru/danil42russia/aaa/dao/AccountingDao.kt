package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.Activity
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

    fun getAllActivity(): List<Activity> {
        val sql = "SELECT id, id_ur, ds, de, vol FROM activity"
        val activityList = mutableListOf<Activity>()

        connection.prepareStatement(sql).use { ps ->
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    activityList.add(
                        Activity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5)
                        )
                    )
                }
            }
        }

        return activityList
    }

    fun getActivityByID(id: Int): List<Activity> {
        val sql = "SELECT id, id_ur, ds, de, vol FROM activity WHERE id = ?"
        val activityList = mutableListOf<Activity>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    activityList.add(
                        Activity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5)
                        )
                    )
                }
            }
        }

        return activityList
    }

    fun getActivityByAuthorityID(authorityId: Int): List<Activity> {
        val sql = "SELECT id, id_ur, ds, de, vol FROM activity WHERE id_ur = ?"
        val activityList = mutableListOf<Activity>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, authorityId)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    activityList.add(
                        Activity(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5)
                        )
                    )
                }
            }
        }

        return activityList
    }
}