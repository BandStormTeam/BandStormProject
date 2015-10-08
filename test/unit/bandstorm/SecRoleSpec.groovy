package bandstorm

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SecRoleSpec extends Specification {


    void "test SecRole methods"() {

        given: "SecRole instances"
        def adminRole = new SecRole('ROLE_ADMIN')
        def userRole = new SecRole('ROLE_USER')


        and: "call object methods"
        def value = adminRole.hashCode()
        def tostring = adminRole.toString()

        expect: "valid return values"
        value instanceof Integer
        tostring == 'ROLE_ADMIN'
        !adminRole.equals(userRole)
    }
}
