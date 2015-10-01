package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Follow)
class FollowSpec extends Specification {

    @Unroll
    void "test validation for a follow"() {

        when: "a new valid follow is created"
        def user1 = Mock(User)
        def user2 = Mock(User)
        Date date = new Date()
        Follow follow = new Follow(dateCreated: date, follower: user1, followed: user2)

        then: "the follow is validate"
        follow.validate()
    }

    void "test invalid follow initialization"(){
        when: "a new invalid follow is created"
        Date date = new Date()
        Follow follow = new Follow(dateCreated: date, follower: user1, followed: user2)

        then: "the follow is invalidate"
        !follow.validate()

        where: user1      | user2
               Mock(User) | null
               null       | Mock(User)

    }
}
