package bandstorm.dao

import bandstorm.Follow
import bandstorm.Status
import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by Dylan on 08/10/2015.
 */
class StatusDaoServiceSpec extends Specification {
    StatusDaoService statusDaoService

    def "test StatusDaoService creation method"(){
        given: "a status"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)

        when: "I want to save this status"
        Status statusRes = statusDaoService.create(status)

        then: "The status is correctly save"
        !statusRes.hasErrors()

        and: "we can found this status"
        Status.findById(statusRes.id) != null
    }

    void "test StatusDaoService update method"(){
        given: "a status"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)
        status = statusDaoService.create(status)

        when: "I want to update this status"
        Status statusRes = statusDaoService.update(status)

        then: "The status is correctly update"
        !statusRes.hasErrors()

        and: "we can found this status"
        Status.findById(statusRes.id) != null
    }

    void "test StatusDaoService delete method"(){
        given: "a status"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)
        status = statusDaoService.create(status)

        when: "I want to delete this status"
        statusDaoService.delete(status)

        then: "The status is correctly delete"
        Status.findById(status.id) == null
    }


    void "test method getLastFollowedStatusOfUser"() {

        given: "a user follow an other"
        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User userFollowed1 = new User(username: "jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate: birthDate,
                country: "France",
                password: "aaaaaaaa").save(flush: true)

        userFollowed1.setPosts(new HashSet<Status>())
        userFollowed1.save(flush: true)
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0,author: userFollowed1).save(flush: true)
            userFollowed1.getPosts().add(status)
        }

        userFollowed1.save(flush: true)


        User userFollowed2 = new User(username: "boby",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate: birthDate,
                country: "France",
                password: "aaaaaaaa").save(flush: true)

        userFollowed2.setPosts(new HashSet<Status>())
        userFollowed2.save(flush: true)
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0,author: userFollowed2).save(flush: true)
            userFollowed2.getPosts().add(status)
        }

        userFollowed2.save(flush: true)

        User user1 = new User(username: "hurican",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate: birthDate,
                country: "France",
                password: "aaaaaaaa").save(flush: true)

        new Follow(followed: userFollowed1,follower: user1).save(flush: true)
        new Follow(followed: userFollowed2,follower: user1).save(flush: true)

        when: "getAllFollowedStatusOfUser is call"
        List<Status> statusListOfFollowed = statusDaoService.getLastFollowedStatusOfUser(user1,0)

        then: "the method return all follower user's status"
        statusListOfFollowed != null
        statusListOfFollowed.size() == 10


    }
}
