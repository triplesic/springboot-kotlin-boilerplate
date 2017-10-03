package org.triplesic.kotlin.boilerplate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.triplesic.kotlin.boilerplate.entity.User
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByName(name: String): Iterable<User>
    fun findByUsername(username: String): Optional<User>
}