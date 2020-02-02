package pl.robert.server.user.domain.dto

data class UpdateUserAgeDto(
        var email: String,
        var newAge: Int
)