package org.triplesic.kotlin.boilerplate.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.triplesic.kotlin.boilerplate.services.AuthenticationService

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authenticationService: AuthenticationService

    override fun configure(web: WebSecurity?) {
        web?.ignoring()
                ?.antMatchers(HttpMethod.POST, "/register", "/loginToApp", "/logoutFromApp")
    }

    override fun configure(http: HttpSecurity?) {
        //TODO("case 1: any request will be authenticated via Basic authenication")
        http
                ?.httpBasic()
                ?.and()
                ?.csrf()?.disable()
                ?.authorizeRequests()
                ?.anyRequest()?.authenticated()
                ?.and()
                ?.formLogin()
    }

//    override fun configure(http: HttpSecurity?) {
//        //TODO("case 2: any request will be authenticated via token filter")
//        //TODO("switch the comment from case 1 to use token authentication logic")
//        http
//                ?.antMatcher("/api/**")?.authorizeRequests()
//                ?.anyRequest()?.authenticated()
//                ?.and()
//                ?.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
//                ?.httpBasic()
//    }

    @Throws(Exception::class)
    fun authenticationTokenFilterBean(): JwtAuthenticationTokenFilter {
        return JwtAuthenticationTokenFilter(authenticationService)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.inMemoryAuthentication()
                ?.withUser("admin")?.password("adminX")?.roles("ADMIN")
                ?.and()
                ?.withUser("user")?.password("userX")?.roles("USER")
                ?.and()
                ?.withUser("guest")?.password("guestX")?.roles("GUEST")
    }
}