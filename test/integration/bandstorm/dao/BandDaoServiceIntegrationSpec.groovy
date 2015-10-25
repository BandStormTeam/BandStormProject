package bandstorm.dao

import bandstorm.Band
import bandstorm.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Test for BandDaoService
 */

class BandDaoServiceIntegrationSpec  extends Specification {
    BandDaoService bandDaoService

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

}