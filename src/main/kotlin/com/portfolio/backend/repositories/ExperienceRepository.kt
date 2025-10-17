package com.portfolio.backend.repositories

import com.portfolio.backend.models.Experience
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository


@Repository
class ExperienceRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    private val rowMapper = RowMapper<Experience> { rs, _ ->
        Experience(
            id = rs.getLong("id"),
            title = rs.getString("title"),
            companyName = rs.getString("company_name"),
            location = rs.getString("location"),
            fromDate = rs.getDate("from_date").toLocalDate(),
            toDate = rs.getDate("to_date")?.toLocalDate(),
            description = rs.getString("description")
        )
    }

    fun getAllExperiences(userId: Long): List<Experience> =
        jdbcTemplate.query("SELECT * FROM experiences WHERE user_id = ?", rowMapper, userId)

    fun save(experience: Experience, userId: Long) = jdbcTemplate.update(
        "INSERT INTO experiences (title, company_name, location, from_date, to_date, description, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
        experience.title,
        experience.companyName,
        experience.location,
        java.sql.Date.valueOf(experience.fromDate),
        experience.toDate?.let { java.sql.Date.valueOf(it) },
        experience.description,
        userId
    )

    fun deleteExperience(experienceId: Long) = jdbcTemplate.update("DELETE FROM experiences WHERE id = ?", experienceId)

}