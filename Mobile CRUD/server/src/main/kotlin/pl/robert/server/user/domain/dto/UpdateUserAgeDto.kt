package pl.robert.server.user.domain.dto

data class UpdateUserAgeDto(
        var uuid: String,
        var newAge: Int
)