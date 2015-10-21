package bandstorm

import bandstorm.dao.StatusDaoService
import bandstorm.dao.UserDAOService
import bandstorm.service.UserService
import grails.test.mixin.Mock

/**
 * Created by Julian on 21/10/2015.
 */

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserService)
@Mock([User, Status])
class UserServiceSpec extends Specification {

    void "test if a status is added"() {

        given: "a status is ready to be added to a user"

        service.statusDaoService = Mock(StatusDaoService) {
            create(_) >> new Status()
        }

        service.userDaoService = Mock(UserDAOService) {
            update(User) >> User
        }

        Date birthDate = Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")

        User user = new User(username: "jack",
                email: "jack@gmail.com",
                firstName: "Paul",
                lastName: "DuBois",
                birthDate: birthDate,
                country: "France",
                password: "aaaaaaaa").save()

        Status status = new Status(url: "www.toto.fr", content: "Coucou", lightCount: 0).save()

        when: "the status is added to the user"
        service.addStatusToUser(user, status)

        then: "the status is added to the user"
        1 * service.statusDaoService.create(status)
        1 * service.userDaoService.update(user)
        user.posts.first() == status

    }
}
