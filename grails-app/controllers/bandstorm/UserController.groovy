package bandstorm

import bandstorm.service.UserService
import bandstorm.service.StatusService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.mapping.UrlMapping
import org.springframework.security.core.context.SecurityContextHolder

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

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    @Secured("ROLE_USER")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    @Secured("ROLE_USER")
    def show(User userInstance) {
        if(userInstance == null) {
            return response.sendError(404)
        }
        
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Secured("ROLE_USER")
    def profilSettings(User userInstance){

        if (userInstance == null){
            userInstance = springSecurityService.getCurrentUser()
        }
        respond userInstance
    }

    @Secured("ROLE_USER")
    def passwordSettings(User userInstance){

        if (userInstance == null){
            userInstance = springSecurityService.getCurrentUser()
        }
        respond userInstance
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

        redirect (action:"index")
    }

    @Secured("ROLE_USER")
    def update(User userInstance,String page) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:page
            return
        }

        userInstance = userDaoService.update(userInstance)

        redirect(action: page)
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
