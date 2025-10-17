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
            fromDate = rs.getString("from_date"),
            toDate = rs.getString("to_date"),
            description = rs.getString("description")
        )
    }

    fun getAllExperiences(userId: Long): List<Experience> =
        jdbcTemplate.query("SELECT * FROM experiences WHERE user_id = ?", rowMapper, userId)

}