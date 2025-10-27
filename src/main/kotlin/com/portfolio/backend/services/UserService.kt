package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.dtos.UserDto
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

    fun getUserById(userId: Long): UserDto {
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
        return UserDto(
            user.name,
            user.country,
            user.role,
            socialMedia,
            user.email,
            user.about,
            user.profileImage
        )
    }

    fun saveUser(userDto: UserDto) {
        val user = User(
            name = userDto.name,
            country = userDto.country,
            role = userDto.role,
            email = userDto.email,
            about = userDto.about,
            profileImage = userDto.profileImage
        )
        userRepository.save(user)
    }

    fun updateUser(userDto: UserDto, id: Long) {
        val user = User(
            name = userDto.name,
            country = userDto.country,
            role = userDto.role,
            email = userDto.email,
            about = userDto.about,
            profileImage = userDto.profileImage
        )
        if (userRepository.findById(id) == null) {
            throw NotFoundException("User not found")
        }
        userRepository.update(user, id)
    }
}
