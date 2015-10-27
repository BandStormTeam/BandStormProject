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
        event.description = "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux"
        def calendar  = Calendar.getInstance()
        calendar.set(2115,10,5)
        event.dateEvent = calendar.getTime();

        when: ""
        def res = event.validate()

        then:""
        res

        and: "the event has no errors"
        !event.hasErrors()

    }

    void "test invalid event"() {
        given: "an event"
        Event event = new Event()
        event.name = aName
        event.description = aDescription
        event.address = anAddress
        event.dateEvent = adateCreated;


        when: "validating an event"
        def isValid = event.validate()

        then: "the event is not valid"
        !isValid

        where:
        aName |  anAddress | aDescription | adateCreated
        "" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux" | null
        "Festival des charrues volantes Festival des charrues volantes Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux" | new Date()
        "Fe" | "8 Avenue des Colombes 31400 Toulouse" | "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux" | null
        "Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse 8 Avenue des Colombes 31400 Toulouse" | "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux" |new Date()
        "Festival des charrues volantes" | "ab" | "Festival organis� sur le th�me de la musique p�ruvienne � travers les mouvements musicaux" | new Date()
        "Festival des charrues volantes" | "8 Avenue des Colombes 31400 Toulouse" | "F"|new Date()

    }
}
