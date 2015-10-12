package bandstorm

import bandstorm.service.UserService
import bandstorm.service.StatusService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import bandstorm.dao.UserDaoService
import org.springframework.security.authentication.AuthenticationManager;

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured("permitAll")
class UserController {
    def springSecurityService
    def logoutHandlers
    AuthenticationManager authenticationManager

    UserService userService
    UserDaoService userDaoService
    StatusService statusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured("ROLE_ADMIN")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        if(userInstance == null) {
            return response.sendError(404)
        }
        
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    def userHome() {
        if (!springSecurityService.isLoggedIn()) {
            try {
                userService.logIn(params?.username, params?.password)
                User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                def statusList = statusService.getStatusForTimeline()
                render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
            } catch (AuthenticationException) {
                redirect(uri: "/")
            }
        } else {
            User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            def statusList = statusService.getStatusForTimeline()
            render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
        }
    }

    def logout() {
        userService.logout(request, response)
        redirect(uri : "/")
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance = userDaoService.create(userInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
