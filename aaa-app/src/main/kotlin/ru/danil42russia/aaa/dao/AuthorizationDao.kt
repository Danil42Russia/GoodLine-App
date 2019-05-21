package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.Role
import ru.danil42russia.aaa.service.BusinessLogic
import java.sql.Connection

class AuthorizationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(BusinessLogic::class.java)

    fun checkRole(role: String): Boolean {
        log.debug("Find role in the DB")
        val sql = "SELECT name FROM roles WHERE name = ?"

        var resultRole = ""

        connection.prepareStatement(sql).use { ps ->
            ps.setString(1, role)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    resultRole = rs.getString(1)
                }
            }
        }

        return resultRole == role
    }

    fun getAllRoles(): List<Role> {
        val sql = "SELECT id, name FROM roles"

        val roleList = mutableListOf<Role>()

        connection.prepareStatement(sql).use { ps ->
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    roleList.add(Role(rs.getInt(1), rs.getString(2)))
                }
            }
        }

        return roleList
    }

    fun getRoleByID(id: Int): Role {
        val sql = "SELECT name FROM roles WHERE id = ?"

        var name = ""

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    name = rs.getString(1)
                }
            }
        }

        return Role(id, name)
    }

    fun getRolesByUserID(userId: Int): List<Role> {
        val sql =
            "SELECT roles.id, roles.name FROM users, roles JOIN users_roles ur ON (users.id = ur.id_user) AND (roles.id = ur.id_role) WHERE users.id = ?"

        val roleList = mutableListOf<Role>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, userId)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    roleList.add(Role(rs.getInt(1), rs.getString(2)))
                }
            }
        }

        return roleList
    }
}