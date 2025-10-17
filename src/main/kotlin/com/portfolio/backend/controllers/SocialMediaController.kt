package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.models.SocialMedia
import com.portfolio.backend.repositories.SocialMediaRepository
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
    private val socialMediaRepository: SocialMediaRepository
) {


    @GetMapping
    fun getSocialMedia(@RequestParam("userId") userId: Long): ResponseEntity<SocialMediaDto> {
        val socialMedia = socialMediaRepository.findByUserId(userId)
        return if (socialMedia != null) {
            val socialMediaDto = SocialMediaDto(
                githubUrl = socialMedia.githubUrl,
                portfolioUrl = socialMedia.portfolioUrl,
                xUrl = socialMedia.xUrl,
                linkedinUrl = socialMedia.linkedinUrl,
                youtubeUrl = socialMedia.youtubeUrl
            )
            ResponseEntity.ok(socialMediaDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @PostMapping
    fun addSocialMedia(
        @RequestParam("userId") userId: Long,
        @RequestBody socialMediaDto: SocialMediaDto,
    ): ResponseEntity<Unit> {
        val socialMedia = SocialMedia(
            githubUrl = socialMediaDto.githubUrl,
            portfolioUrl = socialMediaDto.portfolioUrl,
            xUrl = socialMediaDto.xUrl,
            linkedinUrl = socialMediaDto.linkedinUrl,
            youtubeUrl = socialMediaDto.youtubeUrl
        )
        socialMediaRepository.save(socialMedia, userId)
        return ResponseEntity.ok().build()
    }
}