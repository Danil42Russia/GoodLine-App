package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.Activity
import java.sql.Connection

class AccountingDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)

    fun addActivity(
        login: String,
        res: String,
        role: String,
        ds: String,
        de: String,
        vol: String
    ) {
        log.debug("add activity data to DB")
        val sql =
            "INSERT INTO activity (id_user, res, roles, dataStart, dataEnd, volume) VALUES ((SELECT id FROM users WHERE login = ?), ?, ?, ?, ?, ?)"
        connection.prepareStatement(sql).use { ps ->
            ps.setString(1, login)
            ps.setString(2, res)
            ps.setString(3, role)
            ps.setString(4, ds)
            ps.setString(5, de)
            ps.setInt(6, vol.toInt())
            ps.executeUpdate()
        }
    }

    fun getAllActivity(): List<Activity> {
        val sql =
            "SELECT activity.id, u.login, res, roles, dataStart, dataEnd, volume FROM activity JOIN users u ON activity.id_user = u.id"

        val activityList = mutableListOf<Activity>()

        connection.prepareStatement(sql).use { ps ->
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    activityList.add(
                        Activity(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                        )
                    )
                }
            }
        }

        return activityList
    }

    fun getActivityByID(id: Int): List<Activity> {
        val sql =
            "SELECT activity.id, u.login, res, roles, dataStart, dataEnd, volume FROM activity JOIN users u ON activity.id_user = u.id WHERE activity.id = ?"

        val activityList = mutableListOf<Activity>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    activityList.add(
                        Activity(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                        )
                    )
                }
            }
        }
