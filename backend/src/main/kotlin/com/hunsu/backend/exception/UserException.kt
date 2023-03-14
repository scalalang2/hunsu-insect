package com.hunsu.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class UserException(override val message: String) : RuntimeException(message)

@ControllerAdvice
class UserExceptionHandler {
    @ExceptionHandler(UserException::class)
    fun handleUserException(e: UserException): ResponseEntity<ApiError> {
        return ResponseEntity(ApiError(e.message), HttpStatus.BAD_REQUEST)
    }
}
