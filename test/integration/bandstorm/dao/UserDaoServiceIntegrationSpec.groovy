package bandstorm.dao

import bandstorm.BandController
import bandstorm.User
import grails.test.mixin.*
import spock.lang.*

/**
 * Test for UserDaoService
 */
@TestFor(User)
class UserDaoServiceIntegrationSpec extends Specification {

    UserDaoService userDaoService

    void "test the creation of user"() {

        given: "a user is ready to be created"

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User user = new User(   username:"jack",
                                email: "jack@gmail.com",
                                firstName: "Paul",
                                lastName: "DuBois",
                                birthDate:birthDate,
                                country: "France",
                                password: "aaaaaaaa")


        when: "the user is created by the service"
        user = userDaoService.create(user)

        then: "user is correct"
        !user.hasErrors()

        and: "user exist in the base"
        User.findById(user.getId()) != null


    }

    void "test user's update"() {

        given: "a user is ready to be update"

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User user = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")

        user = userDaoService.create(user)


        when: "the user is update by the service"
        user.setFirstName("Robert")
        user.setCountry("Allemagne")
        user = userDaoService.update(user)

        then: "user is correct"
        !user.hasErrors()

        and: "user exist in the base"
        User.findById(user.getId()) != null

        and: "user is modified"
        user.getFirstName() == "Robert" && user.getCountry() == "Allemagne"


    }


    void "test if user can be deleted"() {

        given: "a user is ready to be deleted"

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User user = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")

        user = userDaoService.create(user)
        Long userId = user.getId()

        when: "the user is delete by the service"
        userDaoService.delete(user)

        then: "user exist in the base"
        User.findById(userId) == null

    }

}
