package bandstorm

import bandstorm.dao.EventDAOService
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(["ROLE_USER","ROLE_ADMIN"])
@Transactional(readOnly = true)
class EventController {

    EventDAOService eventDAOService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 5, 100)
        params.sort = "dateCreated"
        params.order = "desc"
        def calendar = Calendar.getInstance()
        calendar.set(2015,Calendar.SEPTEMBER, 01)
        Event event = new Event(name: "",dateEvent: calendar.getTime() ,description: "", address: "")
        respond Event.list(params), model: [eventInstance: event,eventInstanceCount: Event.count()]
    }

    def show(Event eventInstance) {
        respond eventInstance
    }

    def create() {
        respond new Event(params)
    }

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

    def edit(Event eventInstance) {
        respond eventInstance
    }

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

    @Transactional
    def delete(Event eventInstance) {

        if (eventInstance == null) {
            notFound()
            return
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
