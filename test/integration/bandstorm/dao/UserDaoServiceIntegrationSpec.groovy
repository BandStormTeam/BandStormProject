package bandstorm.dao

import bandstorm.Follow
import bandstorm.Status
import bandstorm.User
import spock.lang.Specification

/**
 * Test for UserDaoService
 */
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

    def "test followUser method"(){

        given:"two users"
        User user1 = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save()
        User user2 = new User(username: "user2", email: "user2@mail.com",
                firstName: "jane", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "qsdfgh").save()

        when:"the user1 want to follow user2"
        Follow myFollow = userDaoService.followUser(user1,user2)

        then:"the follow is OK"
        myFollow != null
        myFollow.id != null
        !myFollow.hasErrors()
    }

    def "test unfollowUser method"(){
        given:"two users"
        User user1 = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        User user2 = new User(username: "user2", email: "user2@mail.com",
                firstName: "jane", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "qsdfgh").save(flush: true)

        and:"user1 follow user2"
        Follow myFollow = userDaoService.followUser(user1,user2)

        when:"user1 want to unfollow user2"
        userDaoService.unfollowUser(user1,user2)

        then:"the follow link is delete"
        Follow.findById(myFollow.id) == null
    }

    def "test findFollowByFollowerAndFollowed method"(){
        given:"a Follow between 2 users"
        User user1 = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        User user2 = new User(username: "user2", email: "user2@mail.com",
                firstName: "jane", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "qsdfgh").save(flush: true)
        userDaoService.followUser(user1,user2)

        when:"I want to find the follow between users"
        Follow myFollow = userDaoService.findFollowByFollowerAndFollowed(user1,user2)

        then:"we get the follow"
        myFollow != null
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
        Map resultMap = userDaoService.getAllUsersByKeywords("mer",10,0)
        List<User> userList = resultMap.userList

        then: "user contains keywords"
        resultMap.totalOfUser == 1
        userList.contains(user1)

        when: "research of all users containing the keywords"
        resultMap = userDaoService.getAllUsersByKeywords("mer",10,0)
        userList = resultMap.userList

        then: "user does not contain keywords"
        resultMap.totalOfUser == 1
        !userList.contains(user2)
    }

    void "test if a status is added"() {

        given: "a status is ready to be added to a user"

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User user = new User(username: "jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate: birthDate,
                country: "France",
                password: "aaaaaaaa").save()

        Status status = new Status(url: "www.toto.fr", content: "Coucou", lightCount: 0)
        status.save(flush:true)

        when: "the status is added to the user"
        userDaoService.addStatusToUser(user, status)

        then: "the status is added to the user"
        user.posts.first() == status
    }


}
