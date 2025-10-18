package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.ExperienceDto
import com.portfolio.backend.models.Experience
import com.portfolio.backend.services.ExperienceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/experience")
class ExperienceController(
    private val experienceService: ExperienceService
) {

    @GetMapping("/all")
    fun getAllExperiences(@RequestParam("userId") userId: Long): ResponseEntity<List<ExperienceDto>> {
        val experiences = experienceService.getAllExperiences(userId)
        return ResponseEntity.ok(experiences)
    }

    @PostMapping
    fun createExperience(
        @RequestBody experienceDto: ExperienceDto,
        @RequestParam("userId") userId: Long
    ): ResponseEntity<Unit> {
        experienceService.saveExperience(experienceDto, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteExperience(@RequestParam("experienceId") experienceId: Long): ResponseEntity<Unit> {
        experienceService.deleteExperience(experienceId)
        return ResponseEntity.ok().build()
    }
}