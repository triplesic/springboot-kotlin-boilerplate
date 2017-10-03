package org.triplesic.kotlin.boilerplate.security

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest?, response: HttpServletResponse?, filterChain: FilterChain?) {
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
        if (token != null)
            return true
        return false
    }
}