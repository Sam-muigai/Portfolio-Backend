package com.portfolio.backend.dtos

import org.springframework.http.HttpStatus

data class OkayResponse(
    val status: Int = HttpStatus.OK.value(),
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
