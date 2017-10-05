package org.triplesic.kotlin.boilerplate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.triplesic.kotlin.boilerplate.entity.Token
import java.time.LocalDateTime

interface TokenRepository : JpaRepository<Token, Long> {
    fun findByUserIdAndExpiredDateGreaterThan(userId: Long, expiredDate: LocalDateTime): Iterable<Token>
    fun findByValueAndExpiredDateGreaterThan(value: String, expiredDate: LocalDateTime): Iterable<Token>
}