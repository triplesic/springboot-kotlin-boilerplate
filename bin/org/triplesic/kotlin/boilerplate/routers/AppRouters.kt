package org.triplesic.kotlin.boilerplate.routers

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.beans.factory.annotation.Autowired
import org.triplesic.kotlin.boilerplate.handlers.UserHandler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configurable
class AppRouters {
	@Autowired lateinit var userHandler: UserHandler

	fun userApi() = router {
		(accept(MediaType.APPLICATION_JSON) and "/users").nest {
			GET("/", userHandler::getAllUsers)
		}
	}
}