package org.triplesic.kotlin.boilerplate.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.http.HttpStatus

@Component
class UserHandler {

	fun getAllUsers(req: ServerRequest): Mono<ServerResponse> {
		println("test first calling method")
		return Mono.justOrEmpty(null)
	}
}