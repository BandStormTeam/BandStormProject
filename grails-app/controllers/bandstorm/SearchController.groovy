package bandstorm

import bandstorm.service.dao.BandDaoService
import bandstorm.service.dao.EventDAOService
import bandstorm.service.dao.UserDaoService
import grails.plugin.springsecurity.annotation.Secured

class SearchController {
    BandDaoService bandDaoService
    UserDaoService userDaoService
    EventDAOService eventDAOService

    /**
     * Return a list of bands corresponding whit keywords
     * @param keywords : inputs for the research
     * @return list of Band
     */
    @Secured("ROLE_USER")
    def band(String keywords,Integer max,Integer offset) {
        if (!max) {
            max = 10
        }
        if (!offset) {
            offset = 0
        }
        if (!keywords) {
            keywords = ""
        }

        def searchResult = bandDaoService.getAllBandsByKeywords(keywords,max,offset)

        render(view: "band", model:[bandList:searchResult.bandList ,keywords:keywords,bandCount:searchResult.bandCount] )
    }

    /**
     * Return a list of users corresponding whit keywords
     * @param keywords : inputs for the research
     * @return list of User
     */
    @Secured("ROLE_USER")
    def user(String keywords,Integer max,Integer offset) {

        if (!max) {
            max = 10
        }
        if (!offset) {
            offset = 0
        }
        if (!keywords) {
            keywords = ""
        }

        def searchResult = userDaoService.getAllUsersByKeywords(keywords,max,offset)

        render(view: "user", model:[userList:searchResult.userList ,keywords:keywords,userCount:searchResult.totalOfUser] )
    }

    /**
     * Return a list of events corresponding whit keywords
     * @param keywords : inputs for the research
     * @return list of events
     */
    @Secured("ROLE_USER")
    def event(String keywords, Integer max, Integer offset) {
        if (!max) {
            max = 10
        }
        if (!offset) {
            offset = 0
        }
        if (!keywords) {
            keywords = ""
        }

        def searchResult = eventDAOService.getAllEventsByKeywords(keywords, max, offset)

        render(view: "event", model:[eventList:searchResult.eventList, keywords:keywords, eventCount:searchResult.eventCount] )
    }
}
