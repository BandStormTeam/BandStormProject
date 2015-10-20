package bandstorm.service

import bandstorm.Band
import bandstorm.User
import grails.transaction.Transactional


/**
 * Service for all Band object
 */
@Transactional
class BandService {


    /**
     * Get all bands corresponding to keywords
     * @param keywords : a string input for the research
     * @param maxItemsForSearch : max item for pagination
     * @param page : value for the pagination
     * @return a Map containing the list of Bands and the total number of result
     */
    Map getAllBandsByKeywords(String keywords,Integer maxItemsForSearch,Integer page){

        Integer max = maxItemsForSearch*page+maxItemsForSearch
        Integer offset =(maxItemsForSearch*page)

        def resultsList = Band.createCriteria().list {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("name", "%" + keyword + "%")
                    ilike("description", "%" + keyword + "%")
                }
            }
            maxResults(max)
            firstResult(offset)
        }

        def resultsCount = Band.createCriteria().count() {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("name", "%" + keyword + "%")
                    ilike("description", "%" + keyword + "%")
                }
            }
        }

        def result = new HashMap()
        result.bandCount = resultsCount.toInteger()
        result.bandList = resultsList.toList()

        return result;
    }
}
