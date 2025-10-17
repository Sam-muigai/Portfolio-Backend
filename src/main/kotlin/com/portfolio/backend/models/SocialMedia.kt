package com.portfolio.backend.models

data class SocialMedia(
    val id: Long? = null,
    val githubUrl: String,
    val portfolioUrl: String,
    val xUrl: String,
    val linkedinUrl: String,
    val youtubeUrl: String,
)
