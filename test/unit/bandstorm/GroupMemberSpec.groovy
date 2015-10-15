package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(GroupMember)
class GroupMemberSpec extends Specification {

    void "test a validate GroupMember creation"() {
        when: "a new valid GroupMember is created"
        def user = Mock(User)
        def band = Mock(Band)
        Date date = new Date()
        GroupMember groupMember = new GroupMember(dateCreated: date,user: user,band: band)

        then: "the groupMember is valid"
        groupMember.validate()
    }

    void "test an unvalidate GroupMember creation"(){
        when: "a new unvalidate Group Member is created"
        Date date = new Date()
        GroupMember groupMember = new GroupMember(dateCreated: date,user: user,band: band)

        then: "the groupMember is invalidate"
        !groupMember.validate()

        where: user       | band
               Mock(User) | null
               null       | Mock(Band)
    }
}
