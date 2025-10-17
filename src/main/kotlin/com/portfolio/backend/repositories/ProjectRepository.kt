package com.portfolio.backend.repositories

import com.portfolio.backend.models.Project
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    private val rowMapper = RowMapper<Project> { rs, _ ->
        Project(
            id = rs.getLong("id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            imageUrl = rs.getString("image_url"),
            projectUrl = rs.getString("project_url")
        )
    }

    fun getAllProjects(userId: Long): List<Project> =
        jdbcTemplate.query("SELECT * FROM projects WHERE user_id = ?", rowMapper, userId)

    fun save(project: Project, userId: Long) = jdbcTemplate.update(
        "INSERT INTO projects (title, description, image_url, project_url, user_id) VALUES (?, ?, ?, ?, ?)",
        project.title, project.description, project.imageUrl, project.projectUrl, userId
    )

    fun deleteProject(projectId: Long) = jdbcTemplate.update("DELETE FROM projects WHERE id = ?", projectId)
}

