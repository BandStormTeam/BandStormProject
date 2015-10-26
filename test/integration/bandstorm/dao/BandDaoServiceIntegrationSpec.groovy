package bandstorm.dao
import bandstorm.Band
import grails.test.mixin.TestFor
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
}
