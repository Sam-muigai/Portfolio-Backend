package com.portfolio.backend.repositories

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository(
    private val jdbcTemplate: JdbcTemplate
) {

}