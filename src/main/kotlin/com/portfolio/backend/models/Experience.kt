package com.portfolio.backend.models

import java.time.LocalDate

data class Experience(
    val id: Long? = null,
    val title: String,
    val companyName: String,
    val location: String,
    val fromDate: LocalDate,
    val toDate: LocalDate?,
    val description: String,
)