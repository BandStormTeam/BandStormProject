package bandstorm.domain

import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.User
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(SecUserSecRole)
class SecUserSecRoleSpec extends Specification {

    void "test SecUserRole methods"() {

        when: "a valid SecUserRole instance and valid User and SecRole instances"
        User user = Mock()
        SecRole role = Mock()
        SecUserSecRole secUserSecRole = new SecUserSecRole(user, role)
        SecUserSecRole test = new SecUserSecRole(null, null)

        and: "return values"
        def value = secUserSecRole.hashCode()
        def resultEqual = secUserSecRole.equals(user)
        def resultRemove = SecUserSecRole.remove(user,null)

        then: "valid return value"
        value instanceof Integer
        !resultEqual
        !resultRemove
        !test.validate()
        secUserSecRole.equals(secUserSecRole)
        !secUserSecRole.equals(null)
    }
}
