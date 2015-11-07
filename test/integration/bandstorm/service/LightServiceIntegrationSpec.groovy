import bandstorm.Light
import bandstorm.Status
import bandstorm.User
import bandstorm.service.LightService
import spock.lang.Specification

/**
 * Created by aroquemaurel on 07/11/15.
 */
class LightServiceIntegrationSpec extends Specification {
    LightService lightService

    void "Test the good beahvior of user lighting a status"() {
        given: "A user and a status"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)

        when: "The user light the status"
        lightService.lightAStatus(user, status)

        then: "The status is lighting by the user"
        Light.findByUser(user) != null
        Light.findByUser(user).status == status
        Light.findByUser(user).user == user
        status.isLighted(user)
        status.nbLight() == 1
    }

    void "Test if a status is lighting by a user "() {
        given: "A user and a status"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        User user2 = new User(username: "user2", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)
        Status status2 = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)

        when: "The user light the status"
        lightService.lightAStatus(user, status)

        then: "The status is lighting by the user"
        lightService.isLightingStatus(user, status)
        !lightService.isLightingStatus(user, status2)
        !lightService.isLightingStatus(user2, status2)
        !lightService.isLightingStatus(user2, status)
        status.isLighted(user)
        !status.isLighted(use2)
        status.nbLight() == 1
    }

    void "Test the good beahvior of user unlighting a status"() {
        given: "A user and a status who he likes"
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe", birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"), country: "somewhere", password: "azerty").save(flush: true)
        Status status = new Status(url: "www.google.fr",content: "a content",lightCount: 0,author: user).save(flush: true)
        lightService.lightAStatus(user, status)

        when: "The user unlight the status"
        lightService.unlightAStatus(user, status)

        then: "The status is not lighting by the user"
        Light.findByUser(user) == null
        !status.isLighted(user)
        status.nbLight() == 0
    }
}
