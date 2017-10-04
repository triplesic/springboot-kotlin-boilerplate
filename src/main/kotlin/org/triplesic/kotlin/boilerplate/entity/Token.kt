package org.triplesic.kotlin.boilerplate.entity

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Token(
        @TableGenerator(name = "token_gen",
                table = "ID_GEN",
                pkColumnName = "GEN_NAME",
                valueColumnName = "GEN_VAL",
                pkColumnValue = "tokn_gen",
                initialValue = 10, allocationSize = 100)
        @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "token_gen")
        var id: Long = 0L,
        @Lob @NotNull @Column(length = 1000)
        var value: String = "",
        var userId: Long = 0L,
        var createdDate: LocalDateTime = LocalDateTime.now(),
        var revisedDate: LocalDateTime = LocalDateTime.now(),
        var expiredDate: LocalDateTime = LocalDateTime.now()
)