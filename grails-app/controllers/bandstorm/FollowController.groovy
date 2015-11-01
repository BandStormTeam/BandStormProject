package bandstorm


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * Follow controller class
 */
@Transactional(readOnly = true)
class FollowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Show the list of follows
     * @param max : max of displayed follows in the page
     * @return the list of follows
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Follow.list(params), model: [followInstanceCount: Follow.count()]
    }

    /**
     * Show details of the Follow instance
     * @param followInstance : follow object
     * @return : details for the follow object
     */
    def show(Follow followInstance) {
        respond followInstance
    }

    /**
     * Create a new instance for Follow
     * @return new Follow instance
     */
    def create() {
        respond new Follow(params)
    }

    /**
     * Save a follow instance
     * @param followInstance :follow object
     * @return Follow's form
     */
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

    /**
     * Edit the Follow instance
     * @param followInstance : Follow object
     * @return an instance of follow
     */
    def edit(Follow followInstance) {
        respond followInstance
    }

    /**
     * Update the follow instance
     * @param followInstance : follow object to edit
     * @return form of the follow object
     */
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

    /**
     * Delete the follow instance
     * @param followInstance : instance of Follow
     * @return the confirmation for deletion
     */
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

    /**
     * Error page, not found
     */
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
