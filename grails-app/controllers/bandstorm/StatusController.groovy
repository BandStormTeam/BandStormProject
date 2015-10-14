package bandstorm

import bandstorm.service.UserService

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured("permitAll")
class StatusController {

    def springSecurityService
    UserService userService
    UserController userController = new UserController()
    AuthenticationManager authenticationManager

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured("ROLE_ADMIN")
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

        //User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())

        addStatus(statusInstance)


        statusInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'status.label', default: 'Status'), statusInstance.id])
            }
            '*' { respond statusInstance, [status: CREATED] }
        }
    }

    def addStatus(Status status) {
        try {
            User user = User.findByUsername("Abel")
            userService.addStatusToUser(user, status)
            System.out.println("TRACE : ")
            System.out.println(user.getPosts().content)
            redirect(controller: "status", action: "index")
        } catch (AuthenticationException) {
            redirect(controller: "status", action: "index")
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
