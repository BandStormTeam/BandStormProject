package bandstorm.dao

import bandstorm.Status
import grails.test.mixin.TestFor
import spock.lang.*

/**
 * Created by Dylan on 08/10/2015.
 */
@TestFor(Status)
class StatusDaoServiceSpec extends Specification {
    StatusDaoService statusDaoService

    void "test StatusDaoService creation method"(){
        given: "a status"
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0)

        when: "I want to save this status"
        Status statusRes = statusDaoService.create(status)

        then: "The status is correctly save"
        !statusRes.hasErrors()

        and: "we can found this status"
        Status.findById(statusRes.id) != null
    }

    void "test StatusDaoService update method"(){
        given: "a status"
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0)
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
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0)
        status = statusDaoService.create(status)

        when: "I want to delete this status"
        statusDaoService.delete(status)

        then: "The status is correctly delete"
        Status.findById(status.id) == null
    }
}
