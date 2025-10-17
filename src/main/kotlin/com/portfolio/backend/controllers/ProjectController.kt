package com.portfolio.backend.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.portfolio.backend.models.Project
import com.portfolio.backend.repositories.ProjectRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectController(
    private val projectRepository: ProjectRepository
) {

    data class ProjectRequest(
        val title: String,
        val description: String,
        @param:JsonProperty("image_url")
        val imageUrl: String,
        @param:JsonProperty("project_url")
        val projectUrl: String,
    )

    data class ProjectResponse(
        val id: Long,
        val title: String,
        val description: String,
        @param:JsonProperty("image_url")
        val imageUrl: String,
        @param:JsonProperty("project_url")
        val projectUrl: String,
    )

    @GetMapping("/{userId}/all")
    fun getProjects(@PathVariable("userId") userId: Long): ResponseEntity<List<ProjectResponse>> {
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

    @PostMapping("/{userId}")
    fun createProject(
        @PathVariable("userId") userId: Long,
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

    @DeleteMapping("/{projectId}")
    fun deleteProject(@PathVariable("projectId") projectId: Long): ResponseEntity<Unit> {
        projectRepository.deleteProject(projectId)
        return ResponseEntity.ok().build()
    }


}