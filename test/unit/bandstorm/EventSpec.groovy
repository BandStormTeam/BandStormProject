package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Event)
class EventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test a valid event"() {
        given: "a event correctly created"
        Event event = new Event()
        event.name = "Festival des charrues volantes"
        event.address = "8 Avenue des Colombes 31400 Toulouse"
        event.description = "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"

        when: ""
        def res = event.validate()

        then:""
        res == true

        and: "the event has no errors"
        !event.hasErrors()

    }

    void "test invalid event"() {
        given: "an event"
        Event event = new Event()
        event.name = aName
        event.description = aDescription
        event.address = anAddress


        when: "validating an event"
        def isValid = event.validate()

        then: "the event is not valid"
        isValid == false

        where:
        aName |  anAddress | aDescription
        "" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"
        "Festival des charrues volantes Festival des charrues volantes Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"
        "Fe" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"
        "Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse" | "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"
        "Festival des charrues volantes" | "ab" | "Festival organisé sur le thème de la musique péruvienne à travers les mouvements musicaux"
        "Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse" | "F"

    }
}
