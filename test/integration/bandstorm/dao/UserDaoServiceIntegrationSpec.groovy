package bandstorm.dao

import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Test for UserDaoService
 */
@TestFor(User)
class UserDaoServiceIntegrationSpec extends Specification {

    UserDAOService userDAOService

    void "test UserDaoService creation method"(){
        given: "a user"
        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")
        User user = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")

        when: "I want to save this user"
        User userRes = userDAOService.create(user)

        then: "The user is correctly save"
        !userRes.hasErrors()

        and: "we can found this user"
        User.findById(userRes.id) != null
    }

    void "test userDaoService update method"(){
        given: "a user"
        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")
        User user = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")
        user = userDAOService.create(user)

        when: "I want to update this user"
        User userRes = userDAOService.update(user)

        then: "The user is correctly update"
        !userRes.hasErrors()

        and: "we can found this user"
        User.findById(userRes.id) != null
    }

    void "test userDaoService delete method"(){
        given: "a user"
        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")
        User user = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")
        user = userDAOService.create(user)

        when: "I want to delete this user"
        userDAOService.delete(user)

        then: "The user is correctly delete"
        User.findById(user.id) == null
    }

}
