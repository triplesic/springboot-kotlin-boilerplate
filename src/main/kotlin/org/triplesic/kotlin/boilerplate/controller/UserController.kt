package org.triplesic.kotlin.boilerplate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.triplesic.kotlin.boilerplate.entity.User
import org.triplesic.kotlin.boilerplate.repository.UserRepository

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(val userRepo: UserRepository){

    @GetMapping
    fun getAllUsers() = userRepo.findAll()

    @PostMapping
    fun addNewUser(@RequestBody user: User) = userRepo.save(user)
}