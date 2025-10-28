package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.ExperienceDto
import com.portfolio.backend.models.Experience
import com.portfolio.backend.repositories.ExperienceRepository
import com.portfolio.backend.repositories.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExperienceService(
    private val userRepository: UserRepository,
    private val experienceRepository: ExperienceRepository
) {

    fun saveExperience(
        experienceDto: ExperienceDto,
        userId: Long
    ) {
        if (userRepository.findById(userId) == null) {
            throw NotFoundException("User not found")
        }
        val experience = Experience(
            title = experienceDto.title,
            companyName = experienceDto.companyName,
            location = experienceDto.location,
            fromDate = LocalDate.parse(experienceDto.fromDate),
            toDate = experienceDto.toDate?.let { LocalDate.parse(it) },
            description = experienceDto.description
        )
        experienceRepository.save(experience, userId)
    }

    fun deleteExperience(experienceId: Long) {
        experienceRepository.deleteExperience(experienceId)
    }

    fun getAllExperiences(userId: Long): List<ExperienceDto> {
        if (userRepository.findById(userId) == null) {
            throw NotFoundException("User not found")
        }
        return experienceRepository.getAllExperiences(userId).map {
            ExperienceDto(
                title = it.title,
                companyName = it.companyName,
                location = it.location,
                fromDate = it.fromDate.toString(),
                toDate = it.toDate?.toString() ?: LocalDate.now().toString(),
                description = it.description
            )
        }
    }
}