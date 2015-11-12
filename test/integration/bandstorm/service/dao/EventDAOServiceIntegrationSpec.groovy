package bandstorm.service.dao
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
        calendar.set(2115,12,5)
    }

    void "test EventDaoService creation method"(){
        given: "a event"
        Event event = new Event(name:"My Event ",dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate")

        when: "I want to save this event"
        Event eventRes = eventDAOService.create(event)

        then: "The event is correctly save"
        !eventRes.hasErrors()

        and: "we can found this event"
        Event.findById(eventRes.id) != null
    }

    void "test EventDaoService update method"(){
        given: "a event"
        Event event =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate")
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
        Event event =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate")
        event = eventDAOService.create(event)

        when: "I want to delete this event"
        eventDAOService.delete(event)

        then: "The event is correctly delete"
        Event.findById(event.id) == null
    }

    void "test if getAllEventsByKeywords is functionnal"() {

        given: "events are ready to be search"
        Event event1 =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate")
        Event event2 =  new Event(name:"My Event ", dateEvent: calendar.getTime(), address:"my long address", description:"my 1st event. please participate")
        event1 = eventDAOService.create(event1)
        event2 = eventDAOService.create(event2)
        List<Event> eventList

        when: "research of all bands containing the keywords"
        Map resultMap = eventDAOService.getAllEventsByKeywords("Eve",10,0)
        eventList = resultMap.eventList

        then: "event1 contains the keywords"
        eventList.size == 10

        when: "research of all events containing the keywords"
        resultMap = eventDAOService.getAllEventsByKeywords("Eve",10,0)
        eventList = resultMap.eventList

        then: "event2 does not contain keywords"
        !eventList.contains(event2)
    }
}
