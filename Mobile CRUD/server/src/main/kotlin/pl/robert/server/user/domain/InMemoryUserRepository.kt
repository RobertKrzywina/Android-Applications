package pl.robert.server.user.domain

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

import kotlin.collections.HashSet

@FieldDefaults(level = AccessLevel.PRIVATE)
class InMemoryUserRepository(var map: ConcurrentHashMap<String, User>) : UserRepository {

    override fun save(user: User) {
        map[user.uuid] = user
    }

    override fun findByUuid(uuid: String): Optional<User> {
        return map.entries
                .stream()
                .filter { map -> map.key == uuid }
                .map { it.value }
                .findFirst()
    }

    override fun findAll(): Iterable<User> = HashSet(map.values)

    override fun deleteByUuid(uuid: String) {
        map.remove(uuid)
    }

    override fun deleteAll() {
        map.clear()
    }
}
