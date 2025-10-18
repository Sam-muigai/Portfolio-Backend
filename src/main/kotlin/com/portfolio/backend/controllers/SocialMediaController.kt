package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.models.SocialMedia
import com.portfolio.backend.repositories.SocialMediaRepository
import com.portfolio.backend.services.SocialMediaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/social-media")
class SocialMediaController(
    private val socialMediaService: SocialMediaService
) {

    @GetMapping
    fun getSocialMedia(@RequestParam("userId") userId: Long): ResponseEntity<SocialMediaDto> {
        return ResponseEntity.ok(socialMediaService.getSocialMedia(userId))
    }

    @PostMapping
    fun addSocialMedia(
        @RequestParam("userId") userId: Long,
        @RequestBody socialMediaDto: SocialMediaDto,
    ): ResponseEntity<Unit> {
        socialMediaService.addSocialMedia(userId, socialMediaDto)
        return ResponseEntity.ok().build()
    }
}