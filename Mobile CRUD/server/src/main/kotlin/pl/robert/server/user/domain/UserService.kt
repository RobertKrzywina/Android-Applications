package pl.robert.server.user.domain

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import pl.robert.server.user.domain.dto.UserDto
import pl.robert.server.user.domain.dto.CreateUserDto
import pl.robert.server.user.domain.exception.InvalidUserException

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserService @Autowired constructor(val repository: UserRepository) {

    @Autowired
    val validator = UserValidator(repository)

    fun save(dtoCreate: CreateUserDto) {
        validator.checkInputData(dtoCreate)
        val user = User()
        user.firstName = dtoCreate.firstName
        user.lastName = dtoCreate.lastName
        user.age = dtoCreate.age
        repository.save(user)
    }

    fun getAll() = repository.findAll()
            .map {
                UserDto(
                        it.uuid,
                        it.firstName,
                        it.lastName,
                        it.age
                )
            }

    @Transactional
    fun updateAge(uuid: String, newAge: Int): User = repository
            .findByUuid(uuid)
            .map { user ->
                user.age = newAge
                user
            }
            .orElseThrow { InvalidUserException(InvalidUserException.CAUSE.UUID_NOT_EXIST) }

    fun deleteByUuid(uuid: String) = repository.deleteByUuid(uuid)

    fun deleteAll() = repository.deleteAll()
}
