package bandstorm.service

import bandstorm.Band
import grails.transaction.Transactional

@Transactional
/**
 * Service for all Band object
 */
class BandService {


    /**
     * Get all bands corresponding to keywords
     * @param keywords : a string input for the research
     * @param maxItemsForSearch : max item for pagination
     * @param page : value for the pagination
     * @return a Map containing the list of Bands and the total number of result
     */
    Map getAllBandsByKeywords(String keywords,Integer maxItemsForSearch,Integer page){

        return null
    }
}
