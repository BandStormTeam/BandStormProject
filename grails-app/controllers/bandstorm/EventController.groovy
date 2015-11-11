package bandstorm

import bandstorm.service.dao.EventDAOService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

/**
 * Event controller class
 */
@Secured(["ROLE_USER","ROLE_ADMIN"])
@Transactional(readOnly = true)
class EventController {

    EventDAOService eventDAOService

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    /**
     * Show the list of user's events
     * @param max : max displayed events by page
     * @return the list of events
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "name"
        params.order = "desc"
        def calendar = Calendar.getInstance()
        calendar.set(2015,Calendar.SEPTEMBER, 01)
        Event event = new Event(name: "",dateEvent: calendar.getTime() ,description: "", address: "")
        respond Event.list(params), model: [eventInstance: event,eventInstanceCount: Event.count()]
    }

    /**
     * Show details of an event
     * @param eventInstance : event to show
     * @return details for event
     */
    def show(Event eventInstance) {
        if(eventInstance == null) {
            return response.sendError(404)
        }

        respond eventInstance
    }

    /**
     * Create an event
     * @return new event
     */
    def create() {
        respond new Event(params)
    }

    /**
     * Save an event instance
     * @param eventInstance : event to save
     * @return event form
     */
    @Transactional
    def save(Event eventInstance) {
        if (eventInstance == null) {
            notFound()
            return
        }

        try {
            eventInstance.name = params.evName
            eventInstance.address = params.evAddress
            eventInstance.description = params.evDescription
            eventInstance.dateEvent = new SimpleDateFormat("dd.MM.yyyy").parse(params.evDate?.replaceAll('/','.'))
            eventInstance.validate()
        } catch (Exception e) {
            e.printStackTrace()
            render template: 'form', model: [eventInstance:eventInstance, status: "KO"]
        }

        if (eventInstance.hasErrors()) {
            render template: 'form', model: [eventInstance:eventInstance, status: "KO"]
            return
        }

        try {
            if(params.evTags) {
                def paramTags = params.evTags.tokenize(';')
                eventInstance.tags = new ArrayList<Tag>()
                paramTags.eachWithIndex { item, index ->
                    def tag = new Tag(name: item).save()
                    eventInstance.tags.add(tag)
            }
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        eventDAOService.create(eventInstance)
        render template: 'form', model: [eventInstance:eventInstance, status: "OK"]
    }

    /**
     * Edit an event
     * @param eventInstance : event to edit
     * @return an event
     */
    def edit(Event eventInstance) {
        respond eventInstance
    }

    /**
     * Update an event
     * @param eventInstance : event to update
     * @return edition form for event
     */
    @Transactional
    def update(Event eventInstance) {
        if (eventInstance == null) {
            notFound()
            return
        }

        if (eventInstance.hasErrors()) {
            respond eventInstance.errors, view: 'edit'
            return
        }

        eventDAOService.update(eventInstance)
    }

    /**
     * Delete an event
     * @param eventInstance : event to delete
     * @return page to confirm the deletion
     */
    @Transactional
    def delete(Event eventInstance) {

        if (eventInstance == null) {
            if(params.id) {
                eventInstance = Event.get(params.id)
            } else {
                notFound()
                return
            }
        }

        eventDAOService.delete(eventInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Event.label', default: 'Event'), eventInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    /**
     * Error page, not found
     */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
