package bandstorm.domain

import bandstorm.User
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SecUserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "verify secUser methods"() {

        given: "a user"
        User user = new User(username: "userName", email: "test@mydomain.com",
                firstName: "jon", lastName: "doe", birthDate: new Date(), country: "somewhere", password: "azerty")
        User user2 = new User(username: "userName", email: "testor@mydomain.com",
                firstName: "jane", lastName: "doe", birthDate: new Date(), country: "somewhere2", password: "azertyr")
        User user3 = new User(username: "adiffname", email: "testor@mydomain.com",
                firstName: "jane", lastName: "doe", birthDate: new Date(), country: "somewhere2", password: "azertyr")

        and: "call object methods"
        def value = user.hashCode()
        def tostring = user.toString()

        expect:"valid return value of called methods"
        value instanceof Integer
        tostring == "userName"
        user.equals(user2) && user.equals(user)
        !user.equals(null)
        !user.equals(user3)


        when: "username is set to null"
        user.username =null

        and: "we try to hash the username"
        def res = user.hashCode()

        then: "a default value is returned"
        res == 0
        !user2.equals(user)

    }
}
