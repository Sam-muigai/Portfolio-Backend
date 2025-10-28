package com.portfolio.backend.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Shared for both request and response.
 * */
data class ExperienceDto(
    val id: Long? = null,
    val title: String,
    @param:JsonProperty("company_name")
    val companyName: String,
    val location: String,
    @param:JsonProperty("from_date")
    val fromDate: String,
    @param:JsonProperty("to_date")
    val toDate: String?,
    val description: String,
)
