package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.dtos.UserResponse
import com.portfolio.backend.models.User
import com.portfolio.backend.repositories.SocialMediaRepository
import com.portfolio.backend.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val socialMediaRepository: SocialMediaRepository,
) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long): UserResponse {
        val user = userRepository.findById(userId) ?: throw NotFoundException("User not found")

        val socialMedia = socialMediaRepository.findByUserId(userId)?.run {
            SocialMediaDto(
                githubUrl = githubUrl,
                portfolioUrl = portfolioUrl,
                xUrl = xUrl,
                linkedinUrl = linkedinUrl,
                youtubeUrl = youtubeUrl
            )
        }
        return UserResponse(
            user.name,
            user.country,
            user.role,
            socialMedia,
            user.email,
            user.about
        )
    }

    fun saveUser(user: User) = userRepository.save(user)
}
