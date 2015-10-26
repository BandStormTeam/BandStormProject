package bandstorm

import bandstorm.dao.BandDaoService
import bandstorm.dao.UserDaoService
import bandstorm.service.StatusService
import bandstorm.service.UserService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

@Transactional(readOnly = true)
@Secured("permitAll")
class UserController {
    def springSecurityService
    def logoutHandlers
    AuthenticationManager authenticationManager

    UserService userService
    UserDaoService userDaoService
    StatusService statusService
    BandDaoService bandDaoService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def show(User userInstance) {
        if(userInstance == null) {
            return response.sendError(404)
        }
        
        def currentUser = springSecurityService.currentUser
        
      respond userInstance, model: [currentUser: currentUser]
    }

    def urlRedirect() {
        if(userService.springSecurityService.isLoggedIn()) {
            redirect (action: "userHome")
        } else {
            redirect(uri: "/index")
        }
    }

    def create() {
        respond new User(params)
    }

    def activateAccount() {
        User userInstance = User.findByUsername(params.username)
        userService.setUserRole(userInstance)
        if(userService.springSecurityService.isLoggedIn()) {
            redirect(action: "logout")
        }
        render (view: "successCreation", model: [type: "activation"])
    }

    /**
     * Return a list of bands corresponding whit keywords
     * @param keywords : inputs for the research
     * @return list of Band
     */
    @Secured("ROLE_USER")
    def searchBand(String keywords,Integer max,Integer offset){
        if (!max){
            max = 10
        }
        if (!offset){
            offset = 0
        }
        if (!keywords){
            keywords = ""
        }

        def searchResult = bandDaoService.getAllBandsByKeywords(keywords,max,offset)

        render(view: "searchBand", model:[bandList:searchResult.bandList ,keywords:keywords,bandCount:searchResult.bandCount] )
    }

    /**
     * Return a list of users corresponding whit keywords
     * @param keywords : inputs for the research
     * @return list of User
     */
    @Secured("ROLE_USER")
    def searchUser(String keywords,Integer max,Integer offset){

        if (!max){
            max = 10
        }
        if (!offset){
            offset = 0
        }
        if (!keywords){
            keywords = ""
        }

        def searchResult = userDaoService.getAllUsersByKeywords(keywords,max,offset)

        render(view: "searchUser", model:[userList:searchResult.userList ,keywords:keywords,userCount:searchResult.totalOfUser] )
    }

    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def profilSettings(User userInstance){
        if (userInstance == null){
            userInstance = User.findByUsername(userService.springSecurityService.getCurrentUser())
        }
        respond userInstance
    }

    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def passwordSettings(User userInstance){

        if (userInstance == null){
            userInstance = userService.springSecurityService.getCurrentUser()
        }
        respond userInstance
    }

    def userHome() {
        if (!userService.springSecurityService.isLoggedIn()) {
            try {
                userService.logIn(params?.username, params?.password)
                User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                def statusList = statusService.getStatusForTimeline()
                render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
            } catch (AuthenticationException) {
                flash.message = "Invalid username or password"
                redirect(uri: "/")
            }
        } else {
            User user = User.findByUsername(userService.springSecurityService.getCurrentUser())
            def statusList = statusService.getStatusForTimeline()
            render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
        }
    }

    def reload() {
        User user = User.findByUsername(userService.springSecurityService.getCurrentUser())
        def statusList = statusService.getStatusForTimeline()
        render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
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
        String url = createLink([action: "activateAccount", absolute: true]).toString()
        userService.contactUser(userInstance.email, userInstance.username, url)

        render (view: "successCreation", model: [username: userInstance.username, type:"success"])
    }

    @Secured(["ROLE_USER","ROLE_ADMIN"])
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

    def followUser(User user){
        def follow = userDaoService.followUser(springSecurityService.currentUser, user)
        redirect(action: "show", params: params)
    }

    def unfollowUser(User user){
        userDaoService.unfollowUser(springSecurityService.currentUser, user)
        redirect(action: "show", params: params)
    }

    def showFollowers(){
        def user = springSecurityService.currentUser
        def followersList = userDaoService.findAllFollowersForUser(user)
        render (view: "userHome", model: [user: user, followersList: followersList])
    }
}
