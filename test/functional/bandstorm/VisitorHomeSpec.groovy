package bandstorm

import bandstorm.page.IndexPage
import geb.spock.GebSpec

/**
 * Created by aroquemaurel on 08/11/15.
 */
class VisitorHomeSpec extends GebSpec {

    def setup() {
        to IndexPage
    }

    def "Test the connection"() {
        when: "We fill the fields"
        $("#username-login").value("merry")
        $("#password-login").value("password")
        $("[name='_action_home']").click()

        then: "we are redirected to the home page"
        $('body').text().contains("Crazy merry")
        $('body').text().contains("Ses groupes")
        $('body').text().contains("Actualité")
        $('body').text().contains("Abonnés")
        $('body').text().contains("Abonnements")
    }

    def "Test the registration"() {
        when: "We fill fields"
        $("#username").value("Pseudal")
        $("#password").value("password")
        $('#email').value('toto@test.fr')
        $('#create').click()
        $('#firstName').value('Machin')
        $('#lastName').value('Stuff')
        $('#country').value('France')
        $('#create').click()

        then: "We are registered in the website"
        $('body').text().contains("Please check you email inbox for activation link")
    }
}
