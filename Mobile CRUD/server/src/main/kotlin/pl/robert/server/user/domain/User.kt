package pl.robert.server.user.domain

import lombok.Getter
import lombok.Setter
import lombok.AccessLevel
import lombok.NoArgsConstructor
import lombok.AllArgsConstructor
import lombok.experimental.FieldDefaults

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity

import java.util.UUID

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
open class User {

    @Id
    var uuid = UUID.randomUUID().toString()

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Column(nullable = false)
    var firstName: String = ""

    @Column(nullable = false)
    var lastName: String = ""

    @Column(nullable = false)
    var age: Int = 0
}
