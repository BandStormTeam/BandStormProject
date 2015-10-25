package bandstorm

import bandstorm.dao.StatusDaoService
import bandstorm.dao.UserDaoService
import bandstorm.service.StatusService
import bandstorm.service.UserService

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager

import static org.springframework.http.HttpStatus.*

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
                //redirect statusInstance
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
