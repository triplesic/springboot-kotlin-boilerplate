package org.triplesic.kotlin.boilerplate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.triplesic.kotlin.boilerplate.entity.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByName(name: String): Iterable<User>
}