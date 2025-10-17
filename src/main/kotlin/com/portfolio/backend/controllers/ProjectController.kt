package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.dtos.ProjectRequest
import com.portfolio.backend.dtos.ProjectResponse
import com.portfolio.backend.models.Project
import com.portfolio.backend.repositories.ProjectRepository
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
    private val projectRepository: ProjectRepository
) {


    @GetMapping("/all")
    fun getProjects(@RequestParam("userId") userId: Long): ResponseEntity<List<ProjectResponse>> {
        val projects = projectRepository.getAllProjects(userId).map { project ->
            ProjectResponse(
                id = project.id!!,
                title = project.title,
                description = project.description,
                imageUrl = project.imageUrl,
                projectUrl = project.projectUrl,
            )
        }
        return ResponseEntity.ok(projects)
    }

    @PostMapping
    fun createProject(
        @RequestParam("userId") userId: Long,
        @RequestBody project: ProjectRequest
    ): ResponseEntity<Unit> {
        val project = Project(
            title = project.title,
            description = project.description,
            imageUrl = project.imageUrl,
            projectUrl = project.projectUrl,
        )
        projectRepository.save(project, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteProject(@RequestParam("projectId") projectId: Long): ResponseEntity<Unit> {
        projectRepository.deleteProject(projectId)
        return ResponseEntity.ok().build()
    }


}