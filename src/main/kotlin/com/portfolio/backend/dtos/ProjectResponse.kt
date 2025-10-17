package com.portfolio.backend.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class ProjectResponse(
    val id: Long,
    val title: String,
    val description: String,
    @param:JsonProperty("image_url")
    val imageUrl: String,
    @param:JsonProperty("project_url")
    val projectUrl: String,
)