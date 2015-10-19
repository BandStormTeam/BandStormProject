package bandstorm

import bandstorm.service.UserService
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by Dylan on 18/10/2015.
 */
@TestFor(User)
class UserServiceIntegrationSpec extends Specification {

    UserService userService

    def "test followUser method"(){

        given:"two users"
        User user1 = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save()
        User user2 = new User(username: "user2", email: "user2@mail.com",
                firstName: "jane", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "qsdfgh").save()

        when:"the user1 want to follow user2"
        Follow myFollow = userService.followUser(user1,user2)

        then:"the follow is OK"
        myFollow != null
        myFollow.id != null
        !myFollow.hasErrors()
    }

    def "test unfollowUser method"(){
        given:"two users"
        User user1 = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(true)
        User user2 = new User(username: "user2", email: "user2@mail.com",
                firstName: "jane", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "qsdfgh").save(true)

        and:"user1 follow user2"
        Follow myFollow = userService.followUser(user1,user2)
        println user1.id
        println myFollow.id

        when:"user1 want to unfollow user2"
        userService.unfollowUser(user1,user2)

        then:"the follow link is delete"
        Follow.findById(myFollow.id) == null
    }
}
