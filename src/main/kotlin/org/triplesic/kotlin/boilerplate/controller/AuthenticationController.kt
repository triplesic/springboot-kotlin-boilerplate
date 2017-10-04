package org.triplesic.kotlin.boilerplate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.triplesic.kotlin.boilerplate.services.AuthenticationService
import org.triplesic.kotlin.boilerplate.entity.User
import org.triplesic.kotlin.boilerplate.repository.UserRepository
import javax.validation.Valid

@RestController
@CrossOrigin(origins = arrayOf("*"))
class AuthenticationController @Autowired constructor(val userRepository: UserRepository, val authenticationService: AuthenticationService) {

    @PostMapping("/loginToApp")
    fun login(@RequestBody user: User): ResponseEntity<*> {
        val canLogin = authenticationService.login(user)
        return when (canLogin) {
            true -> ResponseEntity.ok("Login successful")
            false -> ResponseEntity<String>("Incorrect username or password", HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/register")
    fun registerNewUser(@RequestBody @Valid user: User): ResponseEntity<*> {
        val result = authenticationService.registerNewUser(user)
        if (result) {
            return ResponseEntity.ok("User has been created")
        } else {
            return ResponseEntity<String>("The username already exist", null, HttpStatus.BAD_REQUEST)
        }
    }
}