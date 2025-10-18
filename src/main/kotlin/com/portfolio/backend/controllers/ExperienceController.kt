package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.dtos.ExperienceRequest
import com.portfolio.backend.models.Experience
import com.portfolio.backend.repositories.ExperienceRepository
import com.portfolio.backend.services.ExperienceService
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
    private val experienceService: ExperienceService
) {

    @GetMapping("/all")
    fun getAllExperiences(@RequestParam("userId") userId: Long): ResponseEntity<List<Experience>> {
        val experiences = experienceService.getAllExperiences(userId)
        return ResponseEntity.ok(experiences)
    }

    @PostMapping
    fun createExperience(
        @RequestBody experienceRequest: ExperienceRequest,
        @RequestParam("userId") userId: Long
    ): ResponseEntity<Unit> {
        experienceService.saveExperience(experienceRequest, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteExperience(@RequestParam("experienceId") experienceId: Long): ResponseEntity<Unit> {
        experienceService.deleteExperience(experienceId)
        return ResponseEntity.ok().build()
    }
}