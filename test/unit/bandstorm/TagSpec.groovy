package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tag)
class TagSpec extends Specification {

    void "A tag is valid"() {
        when: "A new tag is created"
        def tag = new Tag(name: aName)
        then: "The validation gives..."
        tag.validate() == isOk

        where: "The tag is valid"
        aName              |  isOk
        "A super tag"      |  true
        "aaaaaaaaaaaaaaaa"  | true // 16 char string
    }

    void "A tag is unvalid"() {
        when: "A new tag is created"
        def tag = new Tag(name: aName)

        then: "The validation gives..."
        tag.validate() == isOk
        where: "The tag is valid"
        aName        | isOk
        ""           | false
        null         | false
        "aaaaaaaaaaaaaaaaa" | false // 17 chars string


    }

}
