package bandstorm.service

import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
class UserServiceSpec extends Specification {

    void "test getAllUserByKeywords method"() {

        given: "userService is inititalised"
        UserService userService = Mock(UserService)

        when: "The service call getAllUserByKeywords"
        userService.getAllUsersByKeywords("keywords here",0)

        then: "getAllUserByKeywords is called"
        1 * userService.getAllUsersByKeywords("keywords here",0)

    }

}
