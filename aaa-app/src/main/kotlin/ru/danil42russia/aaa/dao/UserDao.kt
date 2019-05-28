package ru.danil42russia.aaa.dao

import ru.danil42russia.aaa.domain.data.User
import java.sql.Connection

class UserDao(private val connection: Connection) {
    fun getAllUsers(): List<User> {
        val sql = "SELECT id, login FROM users"

        val list = mutableListOf<User>()

        connection.prepareStatement(sql).use { ps ->
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    list.add(User(rs.getInt(1), rs.getString(2)))
                }

            }
        }

        return list
    }

    fun getUserByID(id: Int): User {
        val sql = "SELECT login FROM users WHERE id = ?"

        var login = ""

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    login = rs.getString(1)
                }
            }
        }

        return User(id, login)
    }
}