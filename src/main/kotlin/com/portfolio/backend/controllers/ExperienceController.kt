package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.models.Experience
import com.portfolio.backend.repositories.ExperienceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/experience")
class ExperienceController(
    private val experienceRepository: ExperienceRepository
) {

    data class ExperienceRequest(
        val title: String,
        @param:JsonProperty("company_name")
        val companyName: String,
        val location: String,
        @param:JsonProperty("from_date")
        val fromDate: String,
        @param:JsonProperty("to_date")
        val toDate: String?,
        val description: String,
    )

    @GetMapping("/{userId}/all")
    fun getAllExperiences(@PathVariable("userId") userId: Long): ResponseEntity<List<Experience>> {
        val experiences = experienceRepository.getAllExperiences(userId)
        return ResponseEntity.ok(experiences)
    }

    @PostMapping("/{userId}")
    fun createExperience(
        @RequestBody experienceRequest: ExperienceRequest,
        @PathVariable("userId") userId: Long
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

    @DeleteMapping("/{experienceId}")
    fun deleteExperience(@PathVariable("experienceId") experienceId: Long): ResponseEntity<Unit> {
        experienceRepository.deleteExperience(experienceId)
        return ResponseEntity.ok().build()
    }
}