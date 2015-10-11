package bandstorm

import bandstorm.service.StatusService
import spock.lang.Specification

/**
 * Created by Dylan on 09/10/2015.
 */
class StatusServiceIntegrationSpec extends Specification {

    StatusService statusService

    void "test getStatusforTimeline method"() {

        given: "2 status"
        def status1 = new Status(content: "My status 1", lightCount: 0).save()
        def status2 = new Status(url: "http://www.google.fr", content: "My status 2", lightCount: 0).save()

        when: "I want to get all status for timeline"
        List<Status> statusList = statusService.getStatusForTimeline()
        println statusList.toString()

        then: "we get all status"
        statusList.size() == 2

    }
}
