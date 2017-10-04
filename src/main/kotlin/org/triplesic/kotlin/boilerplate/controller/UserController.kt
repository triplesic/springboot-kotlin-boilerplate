package org.triplesic.kotlin.boilerplate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.triplesic.kotlin.boilerplate.Services.AuthenticationService
import org.triplesic.kotlin.boilerplate.repository.UserRepository

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(val userRepo: UserRepository) {

    @GetMapping
    fun getAllUsers() = userRepo.findAll()

}