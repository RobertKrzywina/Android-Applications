package pl.robert.server.user.domain

import java.util.Optional

import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, String> {

    fun save(user: User)

    fun findByEmail(email: String): Optional<User>

    fun findAll(): Iterable<User>

    fun deleteByEmail(email: String)

    fun deleteAll()
}
