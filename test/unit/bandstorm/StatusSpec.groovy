package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Status)
class StatusSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "status is valid"() {

        when: "a new status is created"
        Status status = new Status(url:url,content:content,lightCount:lightCount)

        then: "the validation gives..."
        status.validate()

        where: "parameters are ok"
        url                 | content           |lightCount
        "www.google.fr"     | "Bonjour"         |555
        ""                  | "l"               |99999

    }


    void "status is not valid"() {

        when: "a new status is created"
        Status status = new Status(url:url,content:content,lightCount:lightCount)

        then: "the validation gives..."
        !status.validate()

        where: "parameters are not ok"
        url                     | content               |lightCount
        "www.google.fr"         | "Bonjour"             |-2
        "www.facebook.fr"       | "l"                   |100001
        ""                      | "l"                   |100001
        "www.facebook.fr"       | ""                    |1000


    }
}
