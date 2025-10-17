package com.portfolio.backend.models

data class Experience(
    val id: Long,
    val title: String,
    val companyName: String,
    val location: String,
    val fromDate: String,
    val toDate: String?,
    val description: String,
)