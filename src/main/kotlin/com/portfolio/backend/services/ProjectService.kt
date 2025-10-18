package com.portfolio.backend.services

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.ProjectRequest
import com.portfolio.backend.dtos.ProjectResponse
import com.portfolio.backend.models.Project
import com.portfolio.backend.repositories.ProjectRepository
import com.portfolio.backend.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository
) {

    fun getAllProjects(userId: Long): List<ProjectResponse> {
        if (userRepository.findById(userId) == null) throw NotFoundException("User not found")
        val projects = projectRepository.getAllProjects(userId).map { project ->
            ProjectResponse(
                id = project.id!!,
                title = project.title,
                description = project.description,
                imageUrl = project.imageUrl,
                projectUrl = project.projectUrl,
            )
        }
        return projects
    }

    fun createProject(userId: Long, project: ProjectRequest) {
        if (userRepository.findById(userId) == null) throw NotFoundException("User not found")
        projectRepository.save(
            Project(
                title = project.title,
                description = project.description,
                imageUrl = project.imageUrl,
                projectUrl = project.projectUrl,
            ),
            userId
        )
    }

    fun deleteProject(projectId: Long) = projectRepository.deleteProject(projectId)
}