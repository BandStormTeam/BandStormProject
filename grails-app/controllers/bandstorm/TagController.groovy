package bandstorm


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * Controller for tags
 */
@Transactional(readOnly = true)
class TagController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Show the list of tags
     * @param max: max number of tags to show
     * @return the list of tags
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tag.list(params), model: [tagInstanceCount: Tag.count()]
    }

    /**
     * Show details for the tag
     * @param tagInstance : tag instance
     * @return details for the tag
     */
    def show(Tag tagInstance) {
        respond tagInstance
    }

    /**
     * Create new tag instance
     * @return tag object
     */
    def create() {
        respond new Tag(params)
    }

    /**
     * Save the instance of a tag
     * @param tagInstance : object to save
     * @return form of tag
     */
    @Transactional
    def save(Tag tagInstance) {
        if (tagInstance == null) {
            notFound()
            return
        }

        if (tagInstance.hasErrors()) {
            respond tagInstance.errors, view: 'create'
            return
        }

        tagInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])
                redirect tagInstance
            }
            '*' { respond tagInstance, [status: CREATED] }
        }
    }

    /**
     * Edit tag instance
     * @param tagInstance : object to edit
     * @return tag
     */
    def edit(Tag tagInstance) {
        respond tagInstance
    }

    /**
     * Update the tag instance
     * @param tagInstance : object to update
     * @return form for tag
     */
    @Transactional
    def update(Tag tagInstance) {
        if (tagInstance == null) {
            notFound()
            return
        }

        if (tagInstance.hasErrors()) {
            respond tagInstance.errors, view: 'edit'
            return
        }

        tagInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tag.label', default: 'Tag'), tagInstance.id])
                redirect tagInstance
            }
            '*' { respond tagInstance, [status: OK] }
        }
    }

    /**
     * Delete a tag instance
     * @param tagInstance : tag object
     * @return confirmation message for deletion
     */
    @Transactional
    def delete(Tag tagInstance) {

        if (tagInstance == null) {
            notFound()
            return
        }

        tagInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tag.label', default: 'Tag'), tagInstance.id])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
