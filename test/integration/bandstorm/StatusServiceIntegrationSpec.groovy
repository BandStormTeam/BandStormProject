package bandstorm

import bandstorm.service.StatusService
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by Dylan on 09/10/2015.
 */
@TestFor(Status)
class StatusServiceIntegrationSpec extends Specification {

    StatusService statusService

    void "test getStatusforTimeline method"() {

        given: "2 status and 10 status from bootstrap"
        def user1 = new User(username:'user', password:'password', email: 'user@mail.here', firstName:'jon', lastName: 'doe', birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45"), country: 'France')
        def status1 = new Status(content: "My status test 1", lightCount: 0, author: user1).save()
        def status2 = new Status(url: "http://www.google.fr", content: "My status test 2", lightCount: 0, author: user1).save()

        when: "I want to get all status for timeline"
        List<Status> statusList = statusService.getStatusForTimeline()

        then: "we get all status"
        statusList.size() == 2

    }
}
