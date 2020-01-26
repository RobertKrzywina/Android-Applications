package pl.robert.server.user.domain.exception

import lombok.Getter
import lombok.AllArgsConstructor

import pl.robert.server.shared.GlobalExceptionHandler

class InvalidUserException(cause: CAUSE) :
        GlobalExceptionHandler(cause.message, null, false, false) {

    @Getter
    @AllArgsConstructor
    enum class CAUSE constructor(val message: String) {
        UUID_NOT_EXIST("Given uuid does not exist!"),
        EMPTY_FIRST_NAME("Enter first name"),
        EMPTY_LAST_NAME("Enter last name"),
        EMPTY_AGE("Enter age")
    }
}
