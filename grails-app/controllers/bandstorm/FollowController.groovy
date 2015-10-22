package bandstorm


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FollowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Follow.list(params), model: [followInstanceCount: Follow.count()]
    }

    def show(Follow followInstance) {
        respond followInstance
    }

    def create() {
        respond new Follow(params)
    }

    @Transactional
    def save(Follow followInstance) {
        if (followInstance == null) {
            notFound()
            return
        }

        if (followInstance.hasErrors()) {
            respond followInstance.errors, view: 'create'
            return
        }

        followInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'follow.label', default: 'Follow'), followInstance.id])
                redirect followInstance
            }
            '*' { respond followInstance, [status: CREATED] }
        }
    }

    def edit(Follow followInstance) {
        respond followInstance
    }

    @Transactional
    def update(Follow followInstance) {
        if (followInstance == null) {
            notFound()
            return
        }

        if (followInstance.hasErrors()) {
            respond followInstance.errors, view: 'edit'
            return
        }

        followInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Follow.label', default: 'Follow'), followInstance.id])
                redirect followInstance
            }
            '*' { respond followInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Follow followInstance) {

        if (followInstance == null) {
            notFound()
            return
        }

        followInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Follow.label', default: 'Follow'), followInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'follow.label', default: 'Follow'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
