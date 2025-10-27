package com.portfolio.backend.controllers

import com.portfolio.backend.dtos.OkayResponse
import com.portfolio.backend.dtos.UserDto
import com.portfolio.backend.models.User
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
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<OkayResponse> {
        userService.saveUser(userDto)
        return ResponseEntity.ok()
            .body(OkayResponse(message = "User created successful"))

    }

    @GetMapping
    fun getUser(@RequestParam("userId") userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

    @PutMapping
    fun updateUser(
        @RequestParam("userId") userId: Long,
        @RequestBody userDto: UserDto
    ): ResponseEntity<OkayResponse> {
        userService.updateUser(userDto, userId)
        return ResponseEntity.ok()
            .body(OkayResponse(message = "User updated successful"))
    }

}