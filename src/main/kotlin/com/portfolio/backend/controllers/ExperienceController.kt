package com.portfolio.backend.controllers

import com.portfolio.backend.models.Experience
import com.portfolio.backend.repositories.ExperienceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/experience")
class ExperienceController(
    private val experienceRepository: ExperienceRepository
) {

    @GetMapping("/{userId}/all")
    fun getAllExperiences(@PathVariable("userId") userId: Long): ResponseEntity<List<Experience>> {
        val experiences = experienceRepository.getAllExperiences(userId)
        return ResponseEntity.ok(experiences)
    }
}