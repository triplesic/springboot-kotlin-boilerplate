package org.triplesic.kotlin.boilerplate.entity

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Token(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        @Lob @NotNull @Column(length = 1000)
        var value: String = "",
        var userId: Long = 0L,
        var createdDate: LocalDateTime = LocalDateTime.now(),
        var revisedDate: LocalDateTime = LocalDateTime.now(),
        var expiredDate: LocalDateTime = LocalDateTime.now()
)