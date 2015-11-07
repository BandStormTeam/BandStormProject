package bandstorm.service

import bandstorm.Status
import bandstorm.User
import spock.lang.Specification


class StatusServiceIntegrationSpec extends Specification {
    StatusService statusService

    void "test getStatusforTimeline method"() {
        given: "2 status and 20 status from bootstrap"
        def user1 = new User(username:'user', password:'password', email: 'user@mail.here', firstName:'jon', lastName: 'doe', birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45"), country: 'France').save(flush: true)
        new Status(content: "My status test 1", lightCount: 0, author: user1).save(flush: true)
        new Status(url: "http://www.google.fr", content: "My status test 2", lightCount: 0, author: user1).save(flush: true)

        when: "I want to get all status for timeline"
        List<Status> statusList = statusService.getStatusForTimeline()

        then: "we get all status"
        statusList.size() == 10+10+2
    }

    void "test getStatusOfUser method"() {
        given: "New status of a user"
        def user1 = new User(username:'user', password:'password', email: 'user@mail.here', firstName:'jon', lastName: 'doe', birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45"), country: 'France').save(flush: true)
        def user2 = new User(username:'user2', password:'password', email: 'user@mail.here', firstName:'jon', lastName: 'doe', birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45"), country: 'France').save(flush: true)
        new Status(content: "My status test 1", lightCount: 0, author: user1).save(flush: true)
        new Status(url: "http://www.google.fr", content: "My status test 2", lightCount: 0, author: user1).save(flush: true)
        new Status(url: "http://www.google.fr", content: "My status test 2", lightCount: 0, author: user2).save(flush: true)

        when: "I want to get all status of user 1"
        List<Status> statusList = statusService.getStatusOfUser(user1)

        then: "we get all status"
        statusList.size() == 2

    }
}
