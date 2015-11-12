package bandstorm.service

import bandstorm.User
import org.springframework.security.core.AuthenticationException
import spock.lang.Specification

/**
 * Created by aroquemaurel on 12/11/15.
 */
class UserServiceIntegrationSpec extends Specification {
    UserService userService

    void "login test"() {
        when: "We try to log-in"
        boolean ret = true
        try {
            userService.logIn("merry", "password")
        } catch (AuthenticationException e) {
            ret = false
        }
        then: "We do not have error"
        ret

        when: "We try to log-in with incorrect user"
        ret = false
        try {
            userService.logIn("merry2", "password")
        } catch (AuthenticationException e) {
            ret = true
        }
        then: "We have an error"
        ret
    }

    void "test the setting of userrole"() {
        when: "we set a userrole"
        userService.setUserRole(new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        )
        then: "the user has a role"

    }

    void "test the contact of a user"() {
        when: "we try to contact the user"
        userService.contactUser("toto@test.fr", "merry", "http://test.fr")

        then: "We do not have erro"
        true
    }
}
