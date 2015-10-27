package bandstorm.service

import bandstorm.Band
import bandstorm.Follow
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

        User testUser = new User(username:'merry', password:'password', email: 'myeam@somewhere.here', firstName:'jane', lastName: 'doe', birthDate: birth,urlAvatar:'https://haitaar.files.wordpress.com/2010/05/music_by_littlegirl88.jpg', country: 'IT')
        testUser.save()

        def testUser2 = new User(username:'Abel', password:'unlock', email: 'abl@somewhere.here', firstName:'jon', lastName: 'doe', birthDate: birth,urlAvatar:'http://ecx.images-amazon.com/images/I/41HfGlY2XxL._SX450_.jpg', country: 'IT')
        testUser2.save()

        SecUserSecRole.create testUser, userRole, true
        SecUserSecRole.create testUser2, adminRole, true

        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0,author: testUser2).save()
        }

        def band1 = new Band(name: "Les groovy and grails",description: "Un groupe de folie").save()
        def band2 = new Band(name: "Les trois fromages",description: "J'ai un peu faim").save()

        def calendar = Calendar.getInstance()
        for(int i=1; i<=10; i++){
            def status = new Status(content: "My status "+i, lightCount: 0).save()
            calendar.set(2017,10,i)
            def event = new Event(name:"My Event "+i, dateEvent: calendar.getTime(),address:"Palm street Nbr " + i, description:"my " + i + "th event. please participate").save()
            def tag = new Tag(name: "Tag" + i).save()
        }

        new Follow(followed: testUser2,follower: testUser).save(flush: true)
        new Follow(followed: testUser,follower: testUser).save(flush: true)

    }
}
