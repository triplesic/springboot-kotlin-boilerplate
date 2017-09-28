package org.triplesic.kotlin.boilerplate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController class UserController @Autowired constructor(){
    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<Map<Int,String>>{

        return ResponseEntity.ok(mapOf(1 to "A", 2 to "B"))
    }
}