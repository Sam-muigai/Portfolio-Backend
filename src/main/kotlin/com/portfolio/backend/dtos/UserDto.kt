package com.portfolio.backend.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    val name: String,
    val country: String,
    val role: String,
    @param:JsonProperty("social_media")
    val socialMedia: SocialMediaDto? = null,
    val email: String,
    val about: String,
    @param:JsonProperty("profile_image")
    val profileImage: String? = null
)