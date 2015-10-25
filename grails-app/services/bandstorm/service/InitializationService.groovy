package bandstorm.service

import bandstorm.Band
import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.Status
import bandstorm.Tag
import bandstorm.User
import bandstorm.Event
import grails.transaction.Transactional

@Transactional
class InitializationService {

    def populate() {

        SecRole adminRole = new SecRole('ROLE_ADMIN')
        adminRole.save()
        SecRole userRole = new SecRole('ROLE_USER')
        userRole.save()

        Date birth = Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45")

        User testUser = new User(username:'merry', password:'password', email: 'myeam@somewhere.here', firstName:'jane', lastName: 'doe', birthDate: birth, country: 'IT')
        testUser.save()

        def testUser2 = new User(username:'Abel', password:'unlock', email: 'abl@somewhere.here', firstName:'jon', lastName: 'doe', birthDate: birth, country: 'IT')
        testUser2.save()

        SecUserSecRole.create testUser, userRole, true
        SecUserSecRole.create testUser2, adminRole, true

        for(int i=1; i<=10; i++){
            new Status(content: "A super status for Abel "+i, lightCount: 0,author: testUser2).save()
            new Status(content: "A super status for merry "+i, lightCount: 0,author: testUser).save()
        }

        def band1 = new Band(name: "Les groovy and grails", description: "Un groupe de folie").save()
        def band2 = new Band(name: "Les trois fromages", description: "J'ai un peu faim").save() // Thanks to you, me too now !

        def calendar = Calendar.getInstance()
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0).save()
            calendar.set(2017,10,i)
            def event = new Event(name:"My Event "+i, dateEvent: calendar.getTime(),address:"Palm street Nbr " + i, description:"my " + i + "th event. please participate").save()
            def tag = new Tag(name: "Tag" + i).save()
        }
    }
}
