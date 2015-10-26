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
        def band1 = new Band(name: "Les Groovy and Grails",address: "Santa Monica", description: "Etablis non loin de Los Angeles, les Groovy and Grails font entendre leur groove sur les plages de Californie.").save()
        def band2 = new Band(name: "Les Trois Fromages",address: "Pis-Du-Lait", description: "Que vous soyez un amateur ou un expert fromager hors-pair, découvrez le fromage en musique avec Les Trois Fromages").save()

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