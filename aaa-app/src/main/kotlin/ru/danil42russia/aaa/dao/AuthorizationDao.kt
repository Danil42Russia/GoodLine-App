package ru.danil42russia.aaa.dao

import org.apache.logging.log4j.LogManager
import ru.danil42russia.aaa.domain.data.Authority
import java.sql.Connection

class AuthorizationDao(private val connection: Connection) {
    private val log = LogManager.getLogger(AccountingDao::class.java)

    fun getAllAuthority(): List<Authority> {
        val sql = "SELECT id, id_user, id_role, res FROM users_roles"
        val authorityList = mutableListOf<Authority>()

        connection.prepareStatement(sql).use { ps ->
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    authorityList.add(
                        Authority(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4)
                        )
                    )
                }
            }
        }

        return authorityList
    }

    fun getAuthorityByID(id: Int): List<Authority> {
        val sql = "SELECT id, id_user, id_role, res FROM users_roles WHERE id = ?"
        val authorityList = mutableListOf<Authority>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    authorityList.add(
                        Authority(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4)
                        )
                    )
                }
            }
        }

        return authorityList
    }

    fun getAuthorityByUserID(userId: Int): List<Authority> {
        val sql = "SELECT id, id_user, id_role, res FROM users_roles WHERE id_user = ?"
        val authorityList = mutableListOf<Authority>()

        connection.prepareStatement(sql).use { ps ->
            ps.setInt(1, userId)
            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    authorityList.add(
                        Authority(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4)
                        )
                    )
                }
            }
        }

        return authorityList
    }
}