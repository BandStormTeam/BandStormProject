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
            def status = new Status(content: "My status "+i, lightCount: 0,author: testUser2).save()
        }

        def band1 = new Band(name: "Les Groovy and Grails",address: "Santa Monica", description: "Etablis non loin de Los Angeles, les Groovy and Grails repandent leur groove sur les plages de Californie.").save()
        def band2 = new Band(name: "Les Trois Fromages",address: "Pis-Du-Lait", description: "Que vous soyez un amateur ou un expert hors-pair, venez decouvrir le fromage en musique avec Les Trois Fromages.").save()
        def band3 = new Band(name: "Mammoth",address: "Vestmannaeyjar", description: "Voyagez jusqu'en Islande sur les pistes de Mammoth.").save()


        def calendar = Calendar.getInstance()
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0).save()
            calendar.set(2017,10,i)
            def event = new Event(name:"My Event "+i, dateEvent: calendar.getTime(),address:"Palm street Nbr " + i, description:"my " + i + "th event. please participate").save()
            def tag = new Tag(name: "Tag" + i).save()
        }
    }
}
