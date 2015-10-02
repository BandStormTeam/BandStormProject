package bandstorm


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Status.list(params), model: [statusInstanceCount: Status.count()]
    }

    def show(Status statusInstance) {
        respond statusInstance
    }

    def create() {
        respond new Status(params)
    }

    @Transactional
    def save(Status statusInstance) {
        if (statusInstance == null) {
            notFound()
            return
        }

        if (statusInstance.hasErrors()) {
            respond statusInstance.errors, view: 'create'
            return
        }

        statusInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
                redirect statusInstance
            }
            '*' { respond statusInstance, [status: CREATED] }
        }
    }

    def edit(Status statusInstance) {
        respond statusInstance
    }

    @Transactional
    def update(Status statusInstance) {
        if (statusInstance == null) {
            notFound()
            return
        }

        if (statusInstance.hasErrors()) {
            respond statusInstance.errors, view: 'edit'
            return
        }

        statusInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Status.label', default: 'Status'), statusInstance.id])
                redirect statusInstance
            }
            '*' { respond statusInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Status statusInstance) {

        if (statusInstance == null) {
            notFound()
            return
        }

        statusInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Status.label', default: 'Status'), statusInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'status.label', default: 'Status'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
