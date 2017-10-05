package org.triplesic.kotlin.boilerplate.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
                ?.httpBasic()
                ?.and()
                ?.csrf()?.disable()
                ?.authorizeRequests()
                ?.antMatchers(HttpMethod.POST, "/register", "/loginToApp", "/logoutFromApp")?.hasRole("GUEST")
                ?.anyRequest()?.authenticated()
                ?.and()
                ?.formLogin()
    }

//    override fun configure(web: WebSecurity?) {
//        http
//                ?.antMatcher("/api/**")?.authorizeRequests()
//                ?.anyRequest()?.authenticated()
//                ?.and()
//                ?.addFilterBefore(JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
//                //TODO("uncomment this method to implement your token authentication logic")
//    }

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