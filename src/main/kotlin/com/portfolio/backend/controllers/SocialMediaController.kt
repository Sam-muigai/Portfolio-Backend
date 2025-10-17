package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.models.SocialMedia
import com.portfolio.backend.repositories.SocialMediaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/social-media")
class SocialMediaController(
    private val socialMediaRepository: SocialMediaRepository
) {
    data class SocialMediaRequest(
        @param:JsonProperty("github_url")
        val githubUrl: String,
        @param:JsonProperty("portfolio_url")
        val portfolioUrl: String,
        @param:JsonProperty("x_url")
        val xUrl: String,
        @param:JsonProperty("linkedin_url")
        val linkedinUrl: String,
        @param:JsonProperty("youtube_url")
        val youtubeUrl: String,
    )

    @GetMapping("/{userId}")
    fun getSocialMedia(@PathVariable("userId") userId: Long): ResponseEntity<SocialMedia> {
        val socialMedia = socialMediaRepository.findByUserId(userId)
        return if (socialMedia != null) {
            ResponseEntity.ok(socialMedia)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @PostMapping("/{userId}")
    fun addSocialMedia(
        @PathVariable("userId") userId: Long,
        @RequestBody socialMediaRequest: SocialMediaRequest,
    ): ResponseEntity<Unit> {
        val socialMedia = SocialMedia(
            githubUrl = socialMediaRequest.githubUrl,
            portfolioUrl = socialMediaRequest.portfolioUrl,
            xUrl = socialMediaRequest.xUrl,
            linkedinUrl = socialMediaRequest.linkedinUrl,
            youtubeUrl = socialMediaRequest.youtubeUrl
        )
        socialMediaRepository.save(socialMedia, userId)
        return ResponseEntity.ok().build()
    }
}