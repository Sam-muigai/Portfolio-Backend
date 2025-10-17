package com.portfolio.backend.controllers

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


    @GetMapping("/{userId}/all")
    fun getProjects(@PathVariable("userId") userId: Long): ResponseEntity<List<Project>> {
        return ResponseEntity.ok(projectRepository.getAllProjects(userId))
    }

    @PostMapping("/{userId}")
    fun createProject(
        @PathVariable("userId") userId: Long,
        @RequestBody project: Project
    ): ResponseEntity<Unit> {
        projectRepository.save(project, userId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{projectId}")
    fun deleteProject(@PathVariable("projectId") projectId: Long): ResponseEntity<Unit> {
        projectRepository.deleteProject(projectId)
        return ResponseEntity.ok().build()
    }


}