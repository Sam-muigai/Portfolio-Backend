package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.models.SocialMedia
import com.portfolio.backend.repositories.SocialMediaRepository
import com.portfolio.backend.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SocialMediaService(
    private val socialMediaRepository: SocialMediaRepository,
    private val userService: UserRepository,
) {

    fun addSocialMedia(
        userId: Long,
        socialMediaDto: SocialMediaDto
    ) {
        if (userService.findById(userId) == null) throw NotFoundException("User not found")
        val socialMedia = SocialMedia(
            githubUrl = socialMediaDto.githubUrl,
            portfolioUrl = socialMediaDto.portfolioUrl,
            xUrl = socialMediaDto.xUrl,
            linkedinUrl = socialMediaDto.linkedinUrl,
            youtubeUrl = socialMediaDto.youtubeUrl
        )
        socialMediaRepository.save(socialMedia, userId)
    }

    fun getSocialMedia(userId: Long): SocialMediaDto {
        val socialMedia = socialMediaRepository.findByUserId(userId) ?: throw NotFoundException("SocialMedia not found")
        val socialMediaDto = SocialMediaDto(
            githubUrl = socialMedia.githubUrl,
            portfolioUrl = socialMedia.portfolioUrl,
            xUrl = socialMedia.xUrl,
            linkedinUrl = socialMedia.linkedinUrl,
            youtubeUrl = socialMedia.youtubeUrl
        )
        return socialMediaDto
    }

    fun updateSocialMedia(socialMediaDto: SocialMediaDto, userId: Long) {
        if (userService.findById(userId) == null) throw NotFoundException("User not found")
        val socialMedia = SocialMedia(
            githubUrl = socialMediaDto.githubUrl,
            portfolioUrl = socialMediaDto.portfolioUrl,
            xUrl = socialMediaDto.xUrl,
            linkedinUrl = socialMediaDto.linkedinUrl,
            youtubeUrl = socialMediaDto.youtubeUrl
        )
        socialMediaRepository.update(socialMedia, userId)
    }
}