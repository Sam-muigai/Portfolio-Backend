package com.portfolio.backend.models

data class User(
    val id: Long? = null,
    val name: String,
    val country: String,
    val role: String,
    val email: String,
    val about: String,
    val profileImage: String? = null
)