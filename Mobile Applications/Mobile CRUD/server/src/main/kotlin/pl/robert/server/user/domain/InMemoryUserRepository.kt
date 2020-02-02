package pl.robert.server.user.domain

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

import kotlin.collections.HashSet

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryUserRepository(var map: ConcurrentHashMap<String, User>) : UserRepository {

    override fun save(user: User) {
        map[user.email] = user
    }

    override fun findByEmail(email: String): Optional<User> {
        return map.entries
                .stream()
                .filter { map -> map.key == email }
                .map { it.value }
                .findFirst()
    }

    override fun findAll(): Iterable<User> = HashSet(map.values)

    override fun deleteByEmail(email: String) {
        map.remove(email)
    }

    override fun deleteAll() {
        map.clear()
    }
}
