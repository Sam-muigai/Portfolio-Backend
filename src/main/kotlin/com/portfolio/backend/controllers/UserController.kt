package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.UserResponse
import com.portfolio.backend.models.User
import com.portfolio.backend.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userRepository: UserRepository
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
            ResponseEntity.ok(
                UserResponse(
                    user.name,
                    user.country,
                    user.role
                )
            )
        } else {
            ResponseEntity.notFound().build()
        }
    }

}