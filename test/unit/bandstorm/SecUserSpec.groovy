package bandstorm

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
                firstName: "jon", lastName: "doe", birthDate: new Date(), country: "somewhere2", password: "azertyr")

        and: "call object methods"
        def value = user.hashCode()
        def tostring = user.toString()

        expect:"valid return value of called methods"
        value instanceof Integer
        tostring == "userName"
        user.equals(user2)
    }
}
