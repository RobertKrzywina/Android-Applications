package pl.robert.server.user.domain.dto

data class CreateUserDto(
        var email: String,
        var firstName: String,
        var lastName: String,
        var age: Int
)
