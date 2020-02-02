package pl.robert.server.user.domain

import spock.lang.Unroll
import spock.lang.Shared
import spock.lang.Specification

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import java.util.concurrent.ConcurrentHashMap

import pl.robert.server.user.domain.dto.CreateUserDto
import pl.robert.server.user.domain.exception.InvalidUserException

@FieldDefaults(level = AccessLevel.PRIVATE)
class UserSpec extends Specification {

    @Shared
    UserService service

    @Shared
    ConcurrentHashMap<String, User> db = new ConcurrentHashMap<>()

    @Shared
    User user

    def setupSpec() {
        service = new UserService(new InMemoryUserRepository(db))
    }

    def setup() {
        service = new UserService(new InMemoryUserRepository(db))
        CreateUserDto dto = new CreateUserDto('mail@mail.com', 'Doe', 'John', 32)
        service.save(dto)
        user = service.getByEmail(dto.email)
    }

    def cleanup() {
        db.clear()
    }

    def 'Should add user'() {
        when: 'we add user in setup method'

        then: 'system has this user'
        db.size() == 1
    }

    def 'Should find user by email'() {
        when: 'we find user by email'
        User foundUser = service.getByEmail(user.email)

        then: 'system has this user'
        foundUser != null
    }

    def 'Should update user age'() {
        when: 'we update user age'
        User user = service.updateAge(user.email, 55)

        then: 'user has new age'
        user.age == 55
    }

    def 'Should delete user'() {
        when: 'we delete this user'
        service.deleteByEmail(user.email)

        then: 'db should be empty'
        db.isEmpty()
    }

    def 'Should add few users and delete all of them'() {
        when: 'we add few users'
        addUsers()

        and: 'we delete all of them'
        service.deleteAll()

        then: 'db should be empty'
        service.getAll().isEmpty()
    }

    private def addUsers() {
        service.save(new CreateUserDto('x@x.com', 'X', 'X', 20))
        service.save(new CreateUserDto('y@y.com', 'Y', 'Y', 21))
        service.save(new CreateUserDto('z@z.com', 'Z', 'Z', 22))
    }

    @Unroll
    def 'Should throw an exception with specified message cause some of values are blank or empty = [#email, #firstName, #lastName]'(
            String email,
            String firstName,
            String lastName
    ) {
        given: 'initialized obj'
        def dto = new CreateUserDto(email, firstName, lastName, 23)

        when: 'we try to save user'
        service.save(dto)

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message ==
                InvalidUserException.CAUSE.EMPTY_EMAIL.message ||
                InvalidUserException.CAUSE.EMPTY_FIRST_NAME.message ||
                InvalidUserException.CAUSE.EMPTY_LAST_NAME.message

        where:
        email           | firstName | lastName
        ''              | ''        | ''
        'mail@mail.com' | ''        | ''
        'mail@mail.com' | ''        | ''
        'mail@mail.com' | 'John'    | ''
        '             ' | 'John'    | 'Doe'
        'mail@mail.com' | 'John'    | '   '
        'mail@mail.com' | '    '    | '   '
        'mail@mail.com' | '    '    | '   '
        '             ' | '    '    | '   '
    }

    @Unroll
    def 'Should throw an exception cause email format = [#email]'(String email) {
        when: 'we try to create an user'
        service.save(new CreateUserDto(email, 'X', 'X', 23))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_FORMAT.message

        where:
        email                          | _
        'plainaddress'                 | _
        '#@%^%#$@#$@#.com'             | _
        '@domain.com'                  | _
        'Joe Smith <email@domain.com>' | _
        'email.domain.com'             | _
        'email@domain@domain.com'      | _
        '.email@domain.com'            | _
        'email.@domain.com'            | _
        'email..email@domain.com'      | _
        'あいうえお@domain.com'             | _
        'email@domain.com (Joe Smith)' | _
        'email@domain'                 | _
    }

    def 'Should throw an exception cause email need to be unique'() {
        when: 'we initialize email'
        String email = 'john@mail.com'

        and: 'we save user with given email'
        service.save(new CreateUserDto(email, 'X', 'X', 23))

        and: 'we try to save user again with the same email as before'
        service.save(new CreateUserDto(email, 'X', 'X', 23))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_UNIQUE.message
    }

    def 'Should throw an exception cause user does not exists'() {
        when: 'we ask for user by email'
        service.getByEmail('unknown@mail.com')

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_NOT_EXIST.message
    }
}
