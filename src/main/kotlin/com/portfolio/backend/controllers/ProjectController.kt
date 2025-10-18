package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.dtos.OkayResponse
import com.portfolio.backend.dtos.ProjectRequest
import com.portfolio.backend.dtos.ProjectResponse
import com.portfolio.backend.models.Project
import com.portfolio.backend.repositories.ProjectRepository
import com.portfolio.backend.services.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectController(
    private val projectService: ProjectService
) {


    @GetMapping("/all")
    fun getProjects(@RequestParam("userId") userId: Long): ResponseEntity<List<ProjectResponse>> {
        val projects = projectService.getAllProjects(userId)
        return ResponseEntity.ok(projects)
    }

    @PostMapping
    fun createProject(
        @RequestParam("userId") userId: Long,
        @RequestBody project: ProjectRequest
    ): ResponseEntity<OkayResponse> {
        projectService.createProject(userId, project)
        return ResponseEntity.ok()
            .body(OkayResponse(message = "Project added successfully"))
    }

    @DeleteMapping
    fun deleteProject(@RequestParam("projectId") projectId: Long): ResponseEntity<OkayResponse> {
        projectService.deleteProject(projectId)
        return ResponseEntity.ok()
            .body(OkayResponse(message = "Project deleted successfully"))
    }


}