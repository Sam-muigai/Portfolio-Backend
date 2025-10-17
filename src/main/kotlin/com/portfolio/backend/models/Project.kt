package com.portfolio.backend.models


data class Project(
    val id: Long? = null,
    val title: String,
    val description: String,
    val imageUrl: String,
    val projectUrl: String,
)