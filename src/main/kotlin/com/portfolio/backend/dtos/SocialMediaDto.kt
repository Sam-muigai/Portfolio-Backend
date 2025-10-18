package com.portfolio.backend.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Shared for both request and response.
 * */
data class SocialMediaDto(
    @param:JsonProperty("github_url")
    val githubUrl: String,
    @param:JsonProperty("portfolio_url")
    val portfolioUrl: String,
    @param:JsonProperty("x_url")
    @get:JsonProperty("x_url")
    val xUrl: String,
    @param:JsonProperty("linkedin_url")
    val linkedinUrl: String,
    @param:JsonProperty("youtube_url")
    val youtubeUrl: String,
)