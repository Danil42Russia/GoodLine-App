package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.User
import java.sql.Connection

class AuthenticationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)

    /**
     * Looking for a user by login
     *
     * @param login login entered
     *
     * @return User
     */
    fun findUserByLogin(login: String): User? {
        log.debug("Find user by login")
        val sql = "SELECT id, pass, salt FROM users WHERE login = ?"

        var id = -1
        var pass = ""
        var salt = ""

        connection.prepareStatement(sql).use { ps ->
            ps.setString(1, login)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    id = rs.getInt(1)
                    pass = rs.getString(2)
                    salt = rs.getString(3)
                }
            }
        }

        return when {
            id == -1 || pass == "" || salt == "" -> null
            else -> User(id, login, pass, salt)
        }
    }

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