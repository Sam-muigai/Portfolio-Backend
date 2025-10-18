package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.ExperienceRequest
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
        experienceRequest: ExperienceRequest,
        userId: Long
    ) {
        if (userRepository.findById(userId) != null) {
            throw NotFoundException("User not found")
        }
        val experience = Experience(
            title = experienceRequest.title,
            companyName = experienceRequest.companyName,
            location = experienceRequest.location,
            fromDate = LocalDate.parse(experienceRequest.fromDate),
            toDate = experienceRequest.toDate?.let { LocalDate.parse(it) },
            description = experienceRequest.description
        )
        experienceRepository.save(experience, userId)
    }

    fun deleteExperience(experienceId: Long) {
        experienceRepository.deleteExperience(experienceId)
    }

    fun getAllExperiences(userId: Long): List<Experience> {
        if (userRepository.findById(userId) != null) {
            throw NotFoundException("User not found")
        }
        return experienceRepository.getAllExperiences(userId)
    }
}