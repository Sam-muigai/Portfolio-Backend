package com.portfolio.backend.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Project(
    val id: Long,
    val title: String,
    val description: String,
    @param:JsonProperty("image_url")
    val imageUrl: String,
    @param:JsonProperty("project_url")
    val projectUrl: String,
)