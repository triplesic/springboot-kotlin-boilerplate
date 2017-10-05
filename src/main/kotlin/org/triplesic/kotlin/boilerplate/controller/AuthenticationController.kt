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
        val resultMsg = authenticationService.login(user)

        return when (resultMsg.code) {
            HttpStatus.OK -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            HttpStatus.BAD_REQUEST -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            else -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
        }
    }

    @PostMapping("/register")
    fun registerNewUser(@RequestBody @Valid user: User): ResponseEntity<*> {
        val resultMsg = authenticationService.registerNewUser(user)

        return when (resultMsg.code) {
            HttpStatus.OK -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            HttpStatus.BAD_REQUEST -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            else -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
        }
    }

    @PostMapping("/logoutFromApp")
    fun logout(@RequestBody user: User): ResponseEntity<*> {
        val resultMsg = authenticationService.logout(user)

        return when (resultMsg.code) {
            HttpStatus.OK -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            HttpStatus.BAD_REQUEST -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
            else -> ResponseEntity<Any>(resultMsg, resultMsg.code as HttpStatus)
        }
    }
}