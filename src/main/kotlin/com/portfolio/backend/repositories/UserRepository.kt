package com.portfolio.backend.repositories

import com.portfolio.backend.models.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    private val rowMapper = RowMapper<User> { rs, _ ->
        User(
            id = rs.getLong("id"),
            name = rs.getString("user_name"),
            country = rs.getString("country"),
            role = rs.getString("current_user_role"),
            email = rs.getString("email"),
            about = rs.getString("about"),
            profileImage = rs.getString("profile_picture_link")
        )
    }

    fun findAll(): List<User> = jdbcTemplate.query("SELECT * FROM user_table", rowMapper)

    fun save(user: User) = jdbcTemplate.update(
        "INSERT INTO user_table (user_name, country, current_user_role,email,about) VALUES (?, ?, ?,?,?)",
        user.name, user.country, user.role, user.email, user.about
    )

    fun findById(id: Long): User? =
        jdbcTemplate.query("SELECT * FROM user_table WHERE id = ?", rowMapper, id).firstOrNull()

    fun update(user: User,id: Long) = jdbcTemplate.update(
        "UPDATE user_table SET user_name = ?, country = ?, current_user_role = ?,email=?,about=?, profile_picture_link= ? WHERE id = ?",
        user.name, user.country, user.role, user.email, user.about, user.profileImage, id
    )

}









