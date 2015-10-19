package bandstorm.service

import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

@Transactional
class InitializationService {

    def populate() {

        def adminRole = new SecRole('ROLE_ADMIN').save()
        def userRole = new SecRole('ROLE_USER').save()

        Date birth = Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45")

        def testUser = new User(username:'merry', password:'password', email: 'myeam@somewhere.here', firstName:'jane', lastName: 'doe', birthDate: birth, country: 'IT')
        testUser.save()

        def testUser2 = new User(username:'Abel', password:'unlock', email: 'abl@somewhere.here', firstName:'jon', lastName: 'doe', birthDate: birth, country: 'IT')
        testUser2.save()

        SecUserSecRole.create testUser, userRole, true
        SecUserSecRole.create testUser2, adminRole, true


        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0).save()
        }
    }
}
