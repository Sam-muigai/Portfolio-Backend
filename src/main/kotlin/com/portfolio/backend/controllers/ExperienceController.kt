package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.dtos.ExperienceRequest
import com.portfolio.backend.models.Experience
import com.portfolio.backend.repositories.ExperienceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/experience")
class ExperienceController(
    private val experienceRepository: ExperienceRepository
) {

    @GetMapping("/all")
    fun getAllExperiences(@RequestParam("userId") userId: Long): ResponseEntity<List<Experience>> {
        val experiences = experienceRepository.getAllExperiences(userId)
        return ResponseEntity.ok(experiences)
    }

    @PostMapping
    fun createExperience(
        @RequestBody experienceRequest: ExperienceRequest,
        @RequestParam("userId") userId: Long
    ): ResponseEntity<Unit> {
        val experience = Experience(
            title = experienceRequest.title,
            companyName = experienceRequest.companyName,
            location = experienceRequest.location,
            fromDate = LocalDate.parse(experienceRequest.fromDate),
            toDate = experienceRequest.toDate?.let { LocalDate.parse(it) },
            description = experienceRequest.description
        )
        experienceRepository.save(experience, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteExperience(@RequestParam("experienceId") experienceId: Long): ResponseEntity<Unit> {
        experienceRepository.deleteExperience(experienceId)
        return ResponseEntity.ok().build()
    }
}