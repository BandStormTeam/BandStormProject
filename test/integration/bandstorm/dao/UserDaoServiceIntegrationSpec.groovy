package bandstorm.dao

import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification

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

    void "test if getAllUsersByKeywords is functionnal"() {

        given: "users are ready to be search"

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")
        
        User user1 = new User(   username:"merry",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")

        User user2 = new User(   username:"jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate:birthDate,
                country: "France",
                password: "aaaaaaaa")


        user1 = userDaoService.create(user1)
        user2 = userDaoService.create(user2)

        when: "research of all users containing the keywords"
        Map resultMap = userDaoService.getAllUsersByKeywords("mer",0,10)
        List<User> userList = resultMap.userList

        then: "user contains keywords"
        resultMap.totalOfUser == 1
        userList.contains(user1)

        when: "research of all users containing the keywords"
        resultMap = userDaoService.getAllUsersByKeywords("mer",0,10)
        userList = resultMap.userList

        then: "user does not contain keywords"
        resultMap.totalOfUser == 1
        !userList.contains(user2)

    }
}
