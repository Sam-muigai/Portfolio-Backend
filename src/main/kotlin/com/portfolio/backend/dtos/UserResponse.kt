package com.portfolio.backend.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
    val name: String,
    val country: String,
    val role: String,
    @param:JsonProperty("social_media")
    val socialMedia: SocialMediaDto? = null,
)