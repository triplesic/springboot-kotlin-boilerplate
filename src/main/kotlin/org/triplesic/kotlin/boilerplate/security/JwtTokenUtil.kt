package org.triplesic.kotlin.boilerplate.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import org.triplesic.kotlin.boilerplate.entity.User
import java.io.Serializable
import java.time.LocalDateTime


@Component
class JwtTokenUtil : Serializable {

    private val secretKey: String = "SecrEt_Key"

    fun generateToken(user: User, generatedDate: LocalDateTime): String {
        val claims: HashMap<String, Any>
                = hashMapOf(
                CLAIM_KEY_USER_ID to user.id
                , CLAIM_KEY_DATE_CREATED to generatedDate
                , CLAIM_KEY_USER_USERNAME to user.username
                , CLAIM_KEY_USER_NAME to user.name
                , CLAIM_KEY_USER_LASTNAME to user.lastname
        )
        return generateToken(claims)
    }

    private fun generateToken(claims: Map<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact()
    }

    companion object {
        private val serialVersionUID = -3301605591108950415L

        private val CLAIM_KEY_USER_ID = "id"
        private val CLAIM_KEY_USER_USERNAME = "username"
        private val CLAIM_KEY_USER_NAME = "name"
        private val CLAIM_KEY_USER_LASTNAME = "lastname"
        private val CLAIM_KEY_DATE_CREATED = "date_create"

    }
}