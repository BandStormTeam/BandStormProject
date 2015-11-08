package bandstorm

import bandstorm.page.IndexPage
import geb.spock.GebSpec

class MemberHomePageSpec extends GebSpec {
    def setup() {
        to IndexPage
        $("#username-login").value("merry")
        $("#password-login").value("password")
        $("[name='_action_home']").click()
    }

    def cleanup() {
    }

    def "Test to adding status"() {
        String contentField = "Adding a super status";

        when: "We fill the fields"
        $('#contentField').value(contentField)
        $('#publish').click()
        sleep(500) // Wait new status appeared

        then: "We have the new status in timeline"
        $('body').text().contains(contentField)
    }

    def "Test to go on Abel profile"() {
        when: "We click on abel name"
        sleep(500) // Wait status are loaded
        $('#status-19').click()
        then: "we are redirected to the abel profile"
        $('body').text().contains("Abel jon doe")
        $('body').text().contains("Né le 03/04/1992")
    }

    def "Test to show followings"() {
        when: "We click on 'abonnements'"
        $('#followedTimeline').children().click()

        then: "Differents followings are displayed"
        $('body').text().contains("Abel")
        !$('body').text().contains("A super status for merry 10")
        !$('body').text().contains("Posté le 2015-11-08 22:18:12.189")
    }

    def "Test to show followers"() {
        when: "We click on 'Abonnés'"
        $('#followerTimeline').children().click()

        then: "Differents followers are displayed"
        $('body').text().contains("Abel")
        !$('body').text().contains("A super status for merry 10")
        !$('body').text().contains("Posté le 2015-11-08 22:18:12.189")
    }

    def "Test to light a status"() {
        when: "We light a status"
        $('#lightbtn-6').click()

        then: "we are redirected to the home page"
        $('#unlightbtn-6').text().contains("Unlight (1)")
    }

    def "Test to unlight a status"() {
        when: "We unlight a status"
        $('#unlightbtn-6').click()

        then: "we are redirected to the home page"
        $('#lightbtn-6').text().contains("Light (0)")
    }

    def "Test the deconnection"() {
        when: "We click on the deconnection button"
        $('#deconnection').click()

        then: "We are on the home page visitor"
        $('body').text().contains("Suit tes musiciens favoris et leurs évènements.");
    }

    def "Test the events, bands and settings displaying"() {
        when: "We click on events button"
        $('#events').click()

        then: "We are on events page"
        $('body').text().contains("Mes Événements");

        and: "We click on bands button"
        $('#bands').click()

        then: "We are on bands page"
        $('body').text().contains("Mes groupes")

        and: "We click on settings button"
        $('#settings').click()

        then:
        $('body').text().contains("Bienvenue merry")
    }

    def "Test the events displaying"() {
        when: "We click on the events button"
        $('#deconnection').click()

        then: "We are on the home page visitor"
        $('body').text().contains("Suit tes musiciens favoris et leurs évènements.");
    }
}
