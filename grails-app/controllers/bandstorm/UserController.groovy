package bandstorm
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured("permitAll")
class UserController {

    def springSecurityService
    def logoutHandlers
    AuthenticationManager authenticationManager
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured("ROLE_ADMIN")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    def userHome() {
        if (!springSecurityService.isLoggedIn()) {
            try {
                Authentication newAuthentification = new UsernamePasswordAuthenticationToken(params?.username, params?.password)
                Authentication result = authenticationManager.authenticate(newAuthentification)
                SecurityContextHolder.getContext().setAuthentication(result)
                User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                render(view: "userHome", model: [user : user])
            } catch (AuthenticationException) {
                redirect(uri: "/")
            }
        } else {
            User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            render(view: "userHome", model: [user : user])
        }
    }

    def logout() {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth) {
            logoutHandlers.each  { handler->
                handler.logout(request,response,auth)
            }
        }
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

        userInstance.save flush:true

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
