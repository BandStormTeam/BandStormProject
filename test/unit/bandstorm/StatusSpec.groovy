package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Status)
class StatusSpec extends Specification {

    void "status is valid"() {

        when: "a new status is created"
        Status status = new Status(url:url,content:content,lightCount:lightCount, author: author)

        then: "the validation gives..."
        status.validate()

        where: "parameters are ok"
        url                 | content           |lightCount | author
        "www.google.fr"     | "Bonjour"         |555        | Mock(User)
        ""                  | "l"               |99999      | Mock(User)

    }


    void "status is not valid"() {

        when: "a new status is created"
        Status status = new Status(url:url,content:content,lightCount:lightCount, author: author)

        then: "the validation gives..."
        !status.validate()

        where: "parameters are not ok"
        url                     | content               |lightCount | author
        "www.google.fr"         | "Bonjour"             |-2         | Mock(User)
        "www.facebook.fr"       | "l"                   |100001     | Mock(User)
        ""                      | "l"                   |100001     | Mock(User)
        "www.facebook.fr"       | ""                    |1000       | Mock(User)
        "www.google.fr"         | "Bonjour"             |555        | null



    }
}
