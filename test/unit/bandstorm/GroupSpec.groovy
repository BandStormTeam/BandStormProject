package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Group)
class GroupSpec extends Specification {
    void "A group is valid"() {
        when: "A new group is created"
        def group = new Group(name: aName, description: aDescription)
        then: "The validation gives..."
        group.validate()

        where: "The group is valid"
        aName             |   aDescription
        "A super group"  |  "a group description"

    }

    void "A group is unvalid"() {
        when: "A new group is created"
        def group = new Group(name: aName, description: aDescription)
        then: "The validation gives..."
        !group.validate()

        where: "The group is unvalid"
        aName             | aDescription
        "A super group"  |  null
        null             |  "A description"
        ""               |  "Another description"
        "Another group"  |  ""
        ""               |  ""
        null             |  ""
        null             |  null

    }
}
