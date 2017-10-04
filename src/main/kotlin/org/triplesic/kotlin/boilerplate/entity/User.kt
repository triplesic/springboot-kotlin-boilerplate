package org.triplesic.kotlin.boilerplate.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class User(
        @TableGenerator(name = "user_gen",
                table = "ID_GEN",
                pkColumnName = "GEN_NAME",
                valueColumnName = "GEN_VAL",
                pkColumnValue = "usr_gen",
                initialValue = 10, allocationSize = 100)
        @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_gen")
        var id: Long = 0L,
        @NotNull var username: String = "",
        var name: String = "",
        var lastname: String = "",
        @NotNull var passwordNoEncrypted: String = "",
        var password: ByteArray? = null,
        var passSalt: ByteArray? = null
)