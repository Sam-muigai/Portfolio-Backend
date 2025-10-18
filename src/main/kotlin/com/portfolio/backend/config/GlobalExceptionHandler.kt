package com.portfolio.backend.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Map<String, Any>> {
        val errorResponse: MutableMap<String, Any> = HashMap()
        errorResponse["status"] = HttpStatus.BAD_REQUEST.value()
        errorResponse["message"] = exception.localizedMessage
        errorResponse["timestamp"] = LocalDateTime.now()
        errorResponse["error"] = "Bad Request"
        return ResponseEntity.badRequest().body(errorResponse.toMap())
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: Exception): ResponseEntity<Map<String, Any>> {
        val errorResponse: MutableMap<String, Any> = HashMap()
        errorResponse["status"] = HttpStatus.NOT_FOUND.value()
        errorResponse["message"] = exception.localizedMessage
        errorResponse["timestamp"] = LocalDateTime.now()
        errorResponse["error"] = "Not Found"
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.toMap())
    }
}