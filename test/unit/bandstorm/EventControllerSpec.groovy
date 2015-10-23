package bandstorm

import bandstorm.dao.EventDAOService
import grails.test.mixin.*
import org.springframework.http.HttpStatus
import spock.lang.*

import java.text.SimpleDateFormat

@TestFor(EventController)
@Mock(Event)
class EventControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["name"] = 'eventName'
        params["address"] = 'eventAddress'
        params["description"] = 'eventDescription'
        def calendar  = Calendar.getInstance()
        calendar.set(2115,10,5)
        params["dateEvent"] = calendar.getTime()
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.eventInstanceList
        model.eventInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.eventInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def event = new Event()
        controller.eventDAOService = Mock(EventDAOService)
        controller.params.evName = "r"
        controller.params.evAddress = "r"
        controller.params.evDescription = "r"
        def calendar  = Calendar.getInstance()
        calendar.set(2115,10,5)
        controller.params.evDate = new SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime())
        controller.save(event)

        then: "The create view is rendered again with the correct model"
        Event.count() == 0

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        event = new Event(params)
        controller.params.evName = "a name"
        controller.params.evAddress = "a longue addresse"
        controller.params.evDescription = "a description"
        controller.params.evDate = new SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime())
        controller.eventDAOService = Mock(EventDAOService) {
            create(_) >> new Event(name: "name", dateEvent:calendar.getTime() , address: "home sweet home", description: "there isn't much to say").save()
        }

        controller.save(event)

        then: "A new event was successfully added"
        Event.count() == 1
    }

    void "test save method with null parameter"() {
        when:"we try to save a null object"
        controller.save(null)

        then: "the response value is not found"
        response.status == HttpStatus.NOT_FOUND.value()
    }

    void "test save method on a complete event instance"() {
        given: "an event that has no errors"
        populateValidParams(params)
        def nEvent = new Event(params)
        controller.params.evName = "a name"
        controller.params.evAddress = "a longue addresse"
        controller.params.evDescription = "a description"
        controller.params.evDate = "13/09/2120"
        views['/event/_form.gsp'] = 'mock contents'
        controller.eventDAOService = Mock(EventDAOService) {
            create(_) >> nEvent
        }

        when: "we call the save method"
        controller.save(nEvent)

        then: "we create the tags and add them to the event"
        response.text == 'mock contents'
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def event = new Event(params)
        controller.show(event)

        then: "A model is populated containing the domain instance"
        model.eventInstance == event
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def event = new Event(params)
        controller.edit(event)

        then: "A model is populated containing the domain instance"
        model.eventInstance == event
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/event/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def event = new Event()
        event.validate()
        controller.update(event)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.eventInstance == event

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        event = new Event(params).save(flush: true)
        event.address = "une très longue adresse"
        controller.eventDAOService = Mock(EventDAOService){
            update(_) >> event
        }
        controller.update(event)

        then: "A redirect is issues to the show action"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/event/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def event = new Event(params).save(flush: true)

        then: "It exists"
        Event.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.eventDAOService = Mock(EventDAOService) {
            delete(_) >> event.delete()
        }
        controller.delete(event)

        then: "The instance is deleted"
        Event.count() == 0
        response.redirectedUrl == '/event/index'
        flash.message != null
    }

    void "test index method with max param"() {

        when : "the index action is called with a defined max param"
        controller.index(200)

        then: "the index view is rendered and instance count is 5"
        params.max == 100

    }
}
