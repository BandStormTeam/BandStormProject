package bandstorm

import bandstorm.service.StatusService
import bandstorm.service.UserService
import bandstorm.service.dao.StatusDaoService
import bandstorm.service.dao.UserDaoService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager

import static org.springframework.http.HttpStatus.*

/**
 * Status controller class
 */
@Transactional(readOnly = true)
@Secured(["ROLE_USER","ROLE_ADMIN"])
class StatusController {

    def springSecurityService
    UserService userService
    StatusService statusService
    UserDaoService userDaoService
    StatusDaoService statusDaoService
    AuthenticationManager authenticationManager

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Retrun the timeline of connected user
     * @param page : the page to show
     * @return timeline
     */
    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def connectedUserTimeline(Integer page){

        if(!page){
            page = 0;
        }

        User user = userService.springSecurityService.getCurrentUser()
        List<Status> statusList = statusDaoService.getLastFollowedStatusOfUser(user,page)

        render(view: "timeline", model: [user : user, statusList: statusList])
    }

    /**
     * Show the list of status
     * @param max : max displayed status in this page
     * @return the list of status
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Status.list(params), model: [statusInstanceCount: Status.count()]
    }

    /**
     * Show details for status
     * @param statusInstance : status object
     * @return details for status
     */
    def show(Status statusInstance) {
        respond statusInstance
    }

    /**
     * Create a new status object
     * @return news object status
     */
    def create() {
        respond new Status(params)
    }

    /**
     * Save a status instance
     * @param statusInstance : instance to save
     * @return form for status
     */
    @Transactional
    def save(Status statusInstance) {
        if (statusInstance == null) {
            notFound()
            return
        }

        try {
            User user = User.findByUsername(userService.springSecurityService.getCurrentUser())
            statusInstance.author = user
        } catch (AuthenticationException) {
        }

        statusInstance.save(flush: true)

        if (statusInstance.hasErrors()) {
            redirect(controller: "user", action: "reload")
            return
        }

        addStatus(statusInstance)

    }

    /**
     * Add a status for the current user
     * @param status : status to add
     * @return redirection to home
     */
    def addStatus(Status status) {
        try {
            User user = User.findByUsername(userService.springSecurityService.getCurrentUser())
            userDaoService.addStatusToUser(user, status)
            sleep(1000)
            redirect(controller: "user", action: "reload")

        } catch (AuthenticationException) {
            redirect(controller: "user", action: "userHome")
        }
    }

    /**
     * Edit status instance
     * @param statusInstance : status to edit
     * @return status instance
     */
    def edit(Status statusInstance) {
        respond statusInstance
    }

    /**
     * Update a status
     * @param statusInstance : status object
     * @return form for status
     */
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
                //redirect statusInstance
            }
            '*' { respond statusInstance, [status: OK] }
        }

    }

    /**
     * Delete a status
     * @param statusInstance : status to delete
     * @return confirmation of the deletion
     */
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

    /**
     * Error page, not found
     */
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
