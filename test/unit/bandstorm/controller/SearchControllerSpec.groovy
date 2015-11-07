package bandstorm.controller

import bandstorm.Band
import bandstorm.SearchController
import bandstorm.User
import bandstorm.service.dao.BandDaoService
import bandstorm.service.dao.UserDaoService
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SearchController)
class SearchControllerSpec extends Specification {
    void "Test the searchBand action returns a band list"() {

        given: "BandDaoService exist"
        List bandList = new ArrayList<Band>()
        bandList.push(new Band())

        Map searchResult = new HashMap()
        searchResult.bandList = bandList
        searchResult.bandsCount = 10

        controller.bandDaoService = Mock(BandDaoService) {
            getAllBandsByKeywords(_,_,_) >> searchResult
        }

        when: "The searchBand action is executed"
        controller.band("Bob",10,0)

        then: "The bandList is correct and keywords too"
        model.bandList
        model.keywords == "Bob"

    }

    void "Test the searchUser action returns a user list"() {
        given: "UserService exist"
        List userList = new ArrayList<User>()
        userList.push(new User(firstName:"John"))

        Map searchResult = new HashMap()
        searchResult.userList = userList
        searchResult.userCount = 10

        controller.userDaoService = Mock(UserDaoService) {
            getAllUsersByKeywords(_,_,_) >> searchResult
        }

        when: "The searchUser action is executed"
        controller.user("John",10,0)

        then: "The userList is correct and keywords too"
        model.userList
        model.keywords == "John"

    }}
