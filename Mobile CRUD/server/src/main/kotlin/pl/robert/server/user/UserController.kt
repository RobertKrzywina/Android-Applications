package pl.robert.server.user

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.server.ResponseStatusException

import pl.robert.server.user.domain.UserService
import pl.robert.server.user.domain.dto.CreateUserDto
import pl.robert.server.user.domain.dto.UpdateUserAgeDto


@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/user")
class UserController @Autowired constructor(val service: UserService) {

    @PostMapping("/save")
    fun save(@RequestBody dto: CreateUserDto) {
        try {
            ResponseEntity.ok(service.save(dto))
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @GetMapping
    fun getAll() = ResponseEntity.ok(service.getAll())

    @GetMapping("{email}")
    fun getByEmail(@PathVariable(name = "email") email: String) {
        try {
            ResponseEntity.ok(service.getByEmail(email))
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @PutMapping("/update-age")
    fun updateAge(@RequestBody dto: UpdateUserAgeDto) {
        try {
            ResponseEntity.ok(service.updateAge(dto.email, dto.newAge))
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @DeleteMapping("{email}")
    fun deleteByEmail(@PathVariable(name = "email") email: String) {
        try {
            ResponseEntity.ok(service.deleteByEmail(email))
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @DeleteMapping("/delete")
    fun deleteAll() = ResponseEntity.ok(service.deleteAll())
}
