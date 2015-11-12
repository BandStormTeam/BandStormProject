package bandstorm.domain

import bandstorm.Light
import bandstorm.Status
import bandstorm.User
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Light)
class LightSpec extends Specification {
    @Unroll
    void "test validation for a light"() {

        when: "a new valid light is created"
        def user = Mock(User)
        def status = Mock(Status)
        Date date = new Date()
        Light light = new Light(dateCreated: date, user: user, status: status)

        then: "the follow is validate"
        light.validate()
    }

    void "test invalid light initialization"(){
        when: "a new invalid light is created"
        Date date = new Date()
        Light follow = new Light(dateCreated: date, user: user, status: status)

        then: "the follow is invalidate"
        !follow.validate()

        where: user      | status
        Mock(Light) | null
        null       | Mock(Light)

    }}
