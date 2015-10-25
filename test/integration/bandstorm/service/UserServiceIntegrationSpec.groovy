package bandstorm.service

import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification
/**
 *
 */
@TestFor(User)
class UserServiceIntegrationSpec extends Specification {

    UserService userService
    InitializationService initializationService

//    void "login method"() {
//
//        when: "we try to log the user in"
//        initializationService.populate()
//        userService.logIn(User.get(1).username, "password")
//        then: "we check if the spring security service has the right user logged in"
//        userService.springSecurityService.isLoggedIn()
//        userService.springSecurityService.getCurrentUserId() == User.get(1).id
//    }
}
