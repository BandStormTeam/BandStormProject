package bandstorm.service

import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.User
import bandstorm.dao.UserDAOService
import spock.lang.*

/**
 *
 */
class InitializationServiceIntegrationSpec extends Specification {
    /* même probllème qu'avec userServiceIntegration ou l'appel à la des méthode CRUD
    cause des problèmes


    InitializationService initializationService



    void "populate method"() {
        given: "the initialization service"
        User.count() == 0

        when: "when we insert users in the DB"
        initializationService.populate()

        then: "we check if the right number of users and roles is created"
        User.count() == 2
        SecRole.count() == 2
        SecUserSecRole.count() == 2
    }

    */
}
