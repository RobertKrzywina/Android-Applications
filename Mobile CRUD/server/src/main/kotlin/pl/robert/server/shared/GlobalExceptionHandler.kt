package pl.robert.server.shared

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import pl.robert.server.user.domain.exception.InvalidUserException

@ControllerAdvice
abstract class GlobalExceptionHandler(
        message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) :
        RuntimeException(message, cause, enableSuppression, writableStackTrace) {

    init {
        label = message
    }

    companion object {
        var label: String? = ""
    }

    @ExceptionHandler(value = [(InvalidUserException::class)])
    fun handleException(exception: InvalidUserException): ResponseEntity<Any> {
        return ResponseEntity
                .badRequest()
                .body(exception.message)
    }
}
