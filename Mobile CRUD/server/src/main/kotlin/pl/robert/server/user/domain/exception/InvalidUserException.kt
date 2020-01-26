package pl.robert.server.user.domain.exception

import lombok.Getter
import lombok.AllArgsConstructor

import pl.robert.server.shared.GlobalExceptionHandler

class InvalidUserException(cause: CAUSE) :
        GlobalExceptionHandler(cause.message, null, false, false) {

    @Getter
    @AllArgsConstructor
    enum class CAUSE constructor(val message: String) {
        EMPTY_EMAIL("Enter email"),
        EMPTY_FIRST_NAME("Enter first name"),
        EMPTY_LAST_NAME("Enter last name"),
        EMPTY_AGE("Enter age"),
        EMAIL_FORMAT("Invalid email format"),
        EMAIL_UNIQUE("Email should be unique"),
        EMAIL_NOT_EXIST("Given email does not exist!"),
    }
}
