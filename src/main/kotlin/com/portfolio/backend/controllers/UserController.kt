package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.dtos.UserResponse
import com.portfolio.backend.models.User
import com.portfolio.backend.repositories.SocialMediaRepository
import com.portfolio.backend.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userRepository: UserRepository,
    private val socialMediaRepository: SocialMediaRepository
) {


    @GetMapping("/all")
    fun getUserList() = userRepository.findAll()

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<Unit> {
        userRepository.save(user)
        return ResponseEntity.ok().build()
    }

    @GetMapping()
    fun getUser(@RequestParam("userId") id: Long): ResponseEntity<UserResponse> {
        val user = userRepository.findById(id)
        return if (user != null) {

            val socialMedia = socialMediaRepository.findByUserId(id)?.run {
                SocialMediaDto(
                    githubUrl = githubUrl,
                    portfolioUrl = portfolioUrl,
                    xUrl = xUrl,
                    linkedinUrl = linkedinUrl,
                    youtubeUrl = youtubeUrl
                )
            }

            ResponseEntity.ok(
                UserResponse(
                    user.name,
                    user.country,
                    user.role,
                    socialMedia
                )
            )
        } else {
            ResponseEntity.notFound().build()
        }
    }

}