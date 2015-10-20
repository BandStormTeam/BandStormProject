package bandstorm.service

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BandService)
class BandServiceSpec extends Specification {

    void "test getAllBandsByKeywords method"() {

        when: "The service call getAllBandsByKeywords"
        service.getAllBandsByKeywords()("keywords here",10,0)

        then: "getAllUserByKeywords is called"
        1 * service.getAllBandsByKeywords("keywords here",10,0)

    }
}
