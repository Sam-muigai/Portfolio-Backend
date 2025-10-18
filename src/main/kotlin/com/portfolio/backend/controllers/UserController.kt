package com.portfolio.backend.controllers

import com.portfolio.backend.config.NotFoundException
import com.portfolio.backend.dtos.OkayResponse
import com.portfolio.backend.dtos.SocialMediaDto
import com.portfolio.backend.dtos.UserResponse
import com.portfolio.backend.models.User
import com.portfolio.backend.repositories.SocialMediaRepository
import com.portfolio.backend.repositories.UserRepository
import com.portfolio.backend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/all")
    fun getUserList() = userService.getAllUsers()

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<OkayResponse> {
        userService.saveUser(user)
        return ResponseEntity.ok()
            .body(OkayResponse(message = "User created successful"))

    }

    @GetMapping
    fun getUser(@RequestParam("userId") userId: Long): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

}