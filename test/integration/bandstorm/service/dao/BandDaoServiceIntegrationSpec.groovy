package bandstorm.service.dao
import bandstorm.Band
import bandstorm.User
import spock.lang.Specification

/**
 *
 */
class BandDaoServiceIntegrationSpec extends Specification {
    BandDaoService bandDaoService

    void "test BandDaoService creation method"(){
        given: "a band"
        Band band = new Band(name:"My Band ", address:"my long address", description:"This is my band.")

        when: "I want to save this band"
        Band bandRes = bandDaoService.create(band)

        then: "The band is correctly save"
        !bandRes.hasErrors()

        and: "we can found this band"
        Band.findById(bandRes.id) != null
    }

    void "test BandDaoService update method"(){
        given: "a band"
        Band band = new Band(name:"My Band ", address:"my long address", description:"This is my band.")
        band = bandDaoService.create(band)

        when: "I want to update this band"
        Band bandRes = bandDaoService.update(band)

        then: "The band is correctly update"
        !bandRes.hasErrors()

        and: "we can found this band"
        Band.findById(bandRes.id) != null
    }

    void "test BandDaoService delete method"(){
        given: "a band"
        Band band = new Band(name:"My Band ", address:"my long address", description:"This is my band.")
        band = bandDaoService.create(band)

        when: "I want to delete this band"
        bandDaoService.delete(band)

        then: "The band is correctly delete"
        Band.findById(band.id) == null
    }

    void "test if getAllBandByKeywords is functionnal"() {

        given: "bands are ready to be search"
        def band1 = new Band(name: "Les groovy and grails",description: "Un groupe de folie").save()
        def band2 = new Band(name: "Les trois fromages",description: "J'ai un peu faim").save()

        when: "research of all bands containing the keywords"
        Map resultMap = bandDaoService.getAllBandsByKeywords("groovy",10,0)
        List<Band> bandList = resultMap.bandList

        then: "band1 contains the keywords"
        bandList.contains(band1)

        when: "research of all bands containing the keywords"
        resultMap = bandDaoService.getAllBandsByKeywords("groovy",10,0)
        bandList = resultMap.bandList

        then: "band2 does not contain keywords"
        !bandList.contains(band2)
    }

    void "test if a user join band"() {
        given: "A user and a band"
        def band1 = new Band(name: "Les groovy and grails",description: "Un groupe de folie").save()
        User user = new User(username: "user1", email: "user1@mail.com",
                firstName: "jon", lastName: "doe",
                birthDate: Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45"),
                country: "somewhere",
                password: "azerty").save(flush: true)

        when: "The user join the band"
        bandDaoService.joinBand(user, band1)

        then: "User is in the band"
        user.isInBand(band1)
        user.getBands().contains(band1)
    }
}
