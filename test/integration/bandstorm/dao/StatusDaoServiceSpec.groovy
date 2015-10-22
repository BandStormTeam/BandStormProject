package bandstorm.dao

import bandstorm.Status
import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.*

/**
 * Created by Dylan on 08/10/2015.
 */
class StatusDaoServiceSpec extends Specification {
    StatusDaoService statusDaoService

    void "test StatusDaoService creation method"(){
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
}
