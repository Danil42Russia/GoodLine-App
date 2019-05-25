package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.Activity
import java.sql.Connection

class AccountingDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)

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