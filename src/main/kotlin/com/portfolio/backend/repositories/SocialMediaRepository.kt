package com.portfolio.backend.repositories

import com.portfolio.backend.models.SocialMedia
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class SocialMediaRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    private val rowMapper = RowMapper<SocialMedia> { rs, _ ->
        SocialMedia(
            id = rs.getLong("id"),
            githubUrl = rs.getString("github_url"),
            portfolioUrl = rs.getString("portfolio_url"),
            xUrl = rs.getString("x_url"),
            linkedinUrl = rs.getString("linkedin_url"),
            youtubeUrl = rs.getString("youtube_url")
        )
    }

    fun findByUserId(userId: Long): SocialMedia? = jdbcTemplate.query("SELECT * FROM social_media WHERE user_id = ?", rowMapper, userId).firstOrNull()

    fun save(socialMedia: SocialMedia, userId: Long) = jdbcTemplate.update(
        "INSERT INTO social_media (github_url, portfolio_url, x_url, linkedin_url, youtube_url, user_id) VALUES (?, ?, ?, ?, ?, ?)",
        socialMedia.githubUrl, socialMedia.portfolioUrl, socialMedia.xUrl, socialMedia.linkedinUrl, socialMedia.youtubeUrl, userId
    )

    fun update(socialMedia: SocialMedia, userId: Long) = jdbcTemplate.update(
        "UPDATE social_media SET github_url = ?, portfolio_url = ?, x_url = ?, linkedin_url = ?, youtube_url = ? WHERE user_id = ?",
        socialMedia.githubUrl, socialMedia.portfolioUrl, socialMedia.xUrl, socialMedia.linkedinUrl, socialMedia.youtubeUrl, userId
    )
}