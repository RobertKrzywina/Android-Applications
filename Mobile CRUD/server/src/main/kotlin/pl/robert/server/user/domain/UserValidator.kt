package pl.robert.server.user.domain

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import pl.robert.server.user.domain.dto.CreateUserDto
import pl.robert.server.user.domain.exception.InvalidUserException

import java.util.regex.Pattern

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserValidator @Autowired constructor(val repository: UserRepository) {

    val EMAIL_REGEX: Pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    fun checkInputData(dto: CreateUserDto) {
        checkRequiredData(dto)
        checkEmail(dto.email)
    }

    private fun checkRequiredData(dto: CreateUserDto) {
        var cause: InvalidUserException.CAUSE? = null

        when {
            dto.email.isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_EMAIL
            dto.firstName.isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_FIRST_NAME
            dto.lastName.isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_LAST_NAME
            dto.age.toString().isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_AGE
        }

        if (cause != null) {
            throw InvalidUserException(cause)
        }
    }

    private fun checkEmail(email: String) {
        var cause: InvalidUserException.CAUSE? = null

        when {
            !EMAIL_REGEX.matcher(email).find() -> cause = InvalidUserException.CAUSE.EMAIL_FORMAT
            repository.findByEmail(email).isPresent -> cause = InvalidUserException.CAUSE.EMAIL_UNIQUE
        }

        if (cause != null) {
            throw InvalidUserException(cause)
        }
    }
}
