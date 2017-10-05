package org.triplesic.kotlin.boilerplate.security

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.triplesic.kotlin.boilerplate.services.AuthenticationService

class JwtAuthenticationTokenFilter internal constructor(private val authenticationService: AuthenticationService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest?, response: HttpServletResponse?, filterChain: FilterChain?) {
        println("----- do filter internal -----")
        val token = request?.getHeader("token")
        if (verifyToken(token)) {
            filterChain?.doFilter(request, response)
        } else {
            response?.setStatus(401)
            return
        }
    }

    private fun verifyToken(token: String?): Boolean {
       // TODO("implement your business logic for token authentication here")

        //1. check token is valid (exist and active)
        if (token.isNullOrBlank()) return false

        val isValidToken = authenticationService.isValidToken(token!!)

        if (isValidToken)
            return true
        return false
    }
}