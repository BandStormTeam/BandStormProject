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

        when: "The service call getAllUserByKeywords"
        service.getAllUsersByKeywords("keywords here",10,0)

        then: "getAllUserByKeywords is called"
        1 * service.getAllUsersByKeywords("keywords here",10,0)

    }

}
