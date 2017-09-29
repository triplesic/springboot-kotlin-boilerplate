package org.triplesic.kotlin.boilerplate.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0L,
        var username: String = "",
        var name: String = "",
        var lastname: String = ""
)