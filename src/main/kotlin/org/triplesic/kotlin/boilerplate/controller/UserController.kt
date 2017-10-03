package org.triplesic.kotlin.boilerplate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.triplesic.kotlin.boilerplate.Services.AuthenticationService
import org.triplesic.kotlin.boilerplate.entity.User
import org.triplesic.kotlin.boilerplate.repository.UserRepository
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(val userRepo: UserRepository, val authenticationService: AuthenticationService) {

    @GetMapping
    fun getAllUsers() = userRepo.findAll()

    @PostMapping
    fun addNewUser(@RequestBody user: User) = userRepo.save(user)

    @PostMapping("/register")
    fun registerNewUser(@RequestBody @Valid user: User) : ResponseEntity<*> {
        val result = authenticationService.registerNewUser(user)
        if(result){
            return ResponseEntity.ok("User has been created")
        }else{
            return ResponseEntity<String>("The username already exist", null, HttpStatus.BAD_REQUEST)
        }
    }
}