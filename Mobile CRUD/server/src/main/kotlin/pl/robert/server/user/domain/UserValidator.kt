package pl.robert.server.user.domain

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import pl.robert.server.user.domain.dto.CreateUserDto
import pl.robert.server.user.domain.exception.InvalidUserException

@Component
class UserValidator @Autowired constructor(val repository: UserRepository) {

    fun checkInputData(dto: CreateUserDto) {
        checkRequiredData(dto)
    }

    private fun checkRequiredData(dto: CreateUserDto) {
        var cause: InvalidUserException.CAUSE? = null

        when {
            dto.firstName.isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_FIRST_NAME
            dto.lastName.isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_LAST_NAME
            dto.age.toString().isBlank() -> cause = InvalidUserException.CAUSE.EMPTY_AGE
        }

        if (cause != null) {
            throw InvalidUserException(cause)
        }
    }
}
