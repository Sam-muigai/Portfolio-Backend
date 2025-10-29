package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.ContactDto
import com.portfolio.backend.dtos.OkayResponse
import com.portfolio.backend.services.ContactService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contact")
class ContactController(
    private val contactService: ContactService
) {

    @PostMapping
    fun receiveMessage(
        @RequestBody contactDto: ContactDto
    ): ResponseEntity<OkayResponse> {
        val message = contactService.receiveAndSendMessageToWhatsapp(contactDto)
        return ResponseEntity.ok(
            OkayResponse(
                message = message
            )
        )
    }
}