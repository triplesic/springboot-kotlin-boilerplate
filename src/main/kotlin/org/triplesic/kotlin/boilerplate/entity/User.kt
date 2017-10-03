package org.triplesic.kotlin.boilerplate.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        @NotNull var username: String = "",
        var name: String = "",
        var lastname: String = "",
        @NotNull var passwordNoEncrypted: String = "",
        var password: ByteArray? = null,
        var passSalt: ByteArray? = null
)