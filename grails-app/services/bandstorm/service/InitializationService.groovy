package bandstorm.service

import bandstorm.*
import bandstorm.service.dao.UserDaoService
import grails.transaction.Transactional

/**
 * Service for Initialisation
 */
@Transactional
class InitializationService {

    UserDaoService userDaoService

    /**
     * Populate the base whit some examples
     * @return
     */
    def populate() {

        SecRole adminRole = new SecRole('ROLE_ADMIN')
        adminRole.save()
        SecRole userRole = new SecRole('ROLE_USER')
        userRole.save()

        Date birth = Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45")

        User testUser = new User(username:'merry', password:'password', email: 'myeam@somewhere.here', firstName:'jane', lastName: 'doe', birthDate: birth,urlAvatar:'https://haitaar.files.wordpress.com/2010/05/music_by_littlegirl88.jpg', country: 'IT')
        testUser.save()

        def testUser2 = new User(username:'Abel', password:'unlock', email: 'abl@somewhere.here', firstName:'jon', lastName: 'doe', birthDate: birth,urlAvatar:'http://ecx.images-amazon.com/images/I/41HfGlY2XxL._SX450_.jpg', country: 'IT')
        testUser2.save()

        SecUserSecRole.create testUser, userRole, true
        SecUserSecRole.create testUser2, adminRole, true

        for(int i=1; i<=10; i++){
            new Status(content: "A super status for Abel "+i, lightCount: 0,author: testUser2).save()
            new Status(content: "A super status for merry "+i, lightCount: 0,author: testUser).save()
        }

        def band1 = new Band(name: "Les Groovy and Grails",address: "Santa Monica", description: "Etablis non loin de Los Angeles, les Groovy and Grails repandent leur groove sur les plages de Californie.").save()
        def band2 = new Band(name: "Les Trois Fromages",address: "Pis-Du-Lait", description: "Que vous soyez un amateur ou un expert hors-pair, venez decouvrir le fromage en musique avec Les Trois Fromages.").save()
        def band3 = new Band(name: "Mammoth",address: "Vestmannaeyjar", description: "Voyagez jusqu'en Islande sur les pistes de Mammoth.").save()

        testUser.addToGroupsFollowed(band1)
        testUser.addToGroupsFollowed(band2)
        testUser.addToGroupsFollowed(band3)
        testUser.save()

        testUser2.addToGroupsFollowed(band1)
        testUser2.addToGroupsFollowed(band2)
        testUser2.addToGroupsFollowed(band3)
        testUser2.save()

        def calendar = Calendar.getInstance()
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0).save()
            calendar.set(2017,10,i)
            def event = new Event(name:"My Event "+i, dateEvent: calendar.getTime(),address:"Palm street Nbr " + i, description:"my " + i + "th event. please participate").save()
            def tag = new Tag(name: "Tag" + i).save()
        }

        userDaoService.followUser(testUser2,testUser)
        userDaoService.followUser(testUser,testUser2)
    }
}
