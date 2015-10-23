package bandstorm.dao
import bandstorm.Event
import spock.lang.Specification

/**
 *
 */
class EventDAOServiceIntegrationSpec extends Specification {
    EventDAOService eventDAOService
    def calendar

    def "setup"() {
        calendar = Calendar.getInstance()
        calendar.set(2115,10,5)
    }

    void "test EventDaoService creation method"(){
        given: "a event"
        Event event = new Event(name:"My Event ",dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate").save()

        when: "I want to save this event"
        Event eventRes = eventDAOService.create(event)

        then: "The event is correctly save"
        !eventRes.hasErrors()

        and: "we can found this event"
        Event.findById(eventRes.id) != null
    }

    void "test EventDaoService update method"(){
        given: "a event"
        Event event =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate").save()
        event = eventDAOService.create(event)

        when: "I want to update this event"
        Event eventRes = eventDAOService.update(event)

        then: "The event is correctly update"
        !eventRes.hasErrors()

        and: "we can found this event"
        Event.findById(eventRes.id) != null
    }

    void "test EventDaoService delete method"(){
        given: "a event"
        Event event =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate").save()
        event = eventDAOService.create(event)

        when: "I want to delete this event"
        eventDAOService.delete(event)

        then: "The event is correctly delete"
        Event.findById(event.id) == null
    }
}
