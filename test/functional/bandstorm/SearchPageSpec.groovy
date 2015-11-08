package bandstorm

import bandstorm.page.IndexPage
import bandstorm.page.SearchPage
import geb.spock.GebSpec

/**
 * Created by aroquemaurel on 08/11/15.
 */
class SearchPageSpec extends GebSpec {
    def setup() {
        // Firstly, connection
        to IndexPage
        $("#username-login").value("merry")
        $("#password-login").value("password")
        $("[name='_action_home']").click()

        $("[name='keywords']").value("A")
        $('#search-btn').click()
    }

    def "Test the search of a user"() {
        when: "We search a keyword"
        $("[name='keywords']").value("A")
        $('#search-btn').click()

        then: "We have found user"
        $('body').text().contains("Recherche pour \"A\"")
        $('body').text().contains("merry")
        $('body').text().contains("Abel")
    }

    def "Test the search of a band"() {
        when: "We search a keyword"
        $("[name='keywords']").value("A")
        $('#search-btn').click()
        $('#band-filter').children().click()
        then: "We have found user"
        $('body').text().contains("Recherche pour \"A\"")
        $('body').text().contains("Les Groovy and Grails")
        $('body').text().contains("Les Trois Fromages")
        $('body').text().contains("Mammoth")
    }

    def "Test the search of an event"() {
        when: "We search a keyword"
        $("[name='keywords']").value("A")
        $('#search-btn').click()
        $('#event-filter').children().click()

        then: "We have found user"
        $('body').text().contains("Recherche pour \"A\"")
        for(int i = 1 ; i <= 10 ; ++i) {
            $('body').text().contains("My Event "+i)
        }
    }

}
