package bandstorm

import bandstorm.service.LightService
import bandstorm.service.StatusService
import bandstorm.service.UserService
import bandstorm.service.dao.BandDaoService
import bandstorm.service.dao.EventDAOService
import bandstorm.service.dao.UserDaoService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT

/**
 * User controller class
 */
@Transactional(readOnly = true)
@Secured("permitAll")
class UserController {
    def springSecurityService
    def logoutHandlers
    AuthenticationManager authenticationManager
    StatusService statusService
    UserService userService
    UserDaoService userDaoService
    BandDaoService bandDaoService
    EventDAOService eventDAOService
    LightService lightService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    /**
     * Retrun the list of user
     * @param max : max of user to show in the page
     * @return a list of user
     */
    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def index(Integer max) {
        redirect action: 'userHome', controller: 'user', namespace: null
    }

    /**
     * Show the page of an user
     * @param userInstance : user object
     * @return details for the user and his news
     */
    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def show(User userInstance) {
        if(userInstance == null) {
            return response.sendError(404)
        }
        
        def currentUser = springSecurityService.currentUser
        def statusList = statusService.getStatusOfUser(userInstance)
        respond userInstance, model: [currentUser: currentUser,
                                     statusList: statusList]
    }

    /**
     * Redirect to user home if connected
     * @return redirection to index or userhome
     */
    def urlRedirect() {
        if(userService.springSecurityService.isLoggedIn()) {
            redirect (action: "userHome")
        } else {
            redirect(uri: "/index")
        }
    }

    /**
     * Create an new user
     * @return a new user
     */
    def create() {
        respond new User(params)
    }

    /**
     * Activation of an user
     * @return the activation page
     */
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
    def searchBand(String keywords,Integer max,Integer offset) {
        if (!max) {
            max = 10
        }
        if (!offset) {
            offset = 0
        }
        if (!keywords) {
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
    def searchUser(String keywords,Integer max,Integer offset) {

        if (!max) {
            max = 10
        }
        if (!offset) {
            offset = 0
        }
        if (!keywords) {
            keywords = ""
        }

        def searchResult = userDaoService.getAllUsersByKeywords(keywords,max,offset)

        render(view: "searchUser", model:[userList:searchResult.userList ,keywords:keywords,userCount:searchResult.totalOfUser] )
    }

    /**
     * The page for edition of a profil
     * @param userInstance : user object
     * @return form for user profil
     */
    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def profilSettings(User userInstance) {
        if (userInstance == null) {
            userInstance = User.findByUsername(userService.springSecurityService.getCurrentUser())
        }
        respond userInstance
    }

    /**
     * Page for edition of the password
     * @param userInstance : user to edit
     * @return for for user password
     */
    @Secured(["ROLE_USER","ROLE_ADMIN"])
    def passwordSettings(User userInstance) {

        if (userInstance == null) {
            userInstance = userService.springSecurityService.getCurrentUser()
        }
        respond userInstance
    }

    /**
     * User homepage, show the news(status) of an user
     * @return the homepage of the user
     */
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

    /**
     * Homepage of the user page after the adding of status
     * @return homepage of the user
     */
    def reload() {
        User user = User.findByUsername(userService.springSecurityService.getCurrentUser())
        def statusList = statusService.getStatusForTimeline()
        render(view: "userHome", model: [user : user, statusList: statusList, statusCount: statusList.size()])
    }

    /**
     * Logout the user
     * @return landing page
     */
    def logout() {
        userService.logout(request, response)
        redirect(uri : "/")
    }

    /**
     * Save the instance of a user
     * @param userInstance : user to save
     * @return edition form for a user
     */
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

    /**
     * Update an user
     * @param userInstance: user to uodate
     * @param page: page to show
     * @return the page to show
     */
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

    /**
     * Delete an user
     * @param userInstance : user to delete
     * @return message of deletion
     */
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

    /**
     * Error page, not found
     */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * Follow an user
     * @param user : user to follow
     * @return page of the user
     */
    def followUser(User user) {
        def follow = userDaoService.followUser(springSecurityService.currentUser, user)
        redirect(action: "show", params: params)
    }

    /**
     * Unfollow an user
     * @param user : user to unfollow
     * @return page of the user
     */
    def unfollowUser(User user) {
        userDaoService.unfollowUser(springSecurityService.currentUser, user)
        redirect(action: "show", params: params)
    }

    /**
     * Show the followers of an user
     * @return list of followers
     */
    def showFollowers() {
        def user = springSecurityService.currentUser
        def followersList = userDaoService.findAllFollowersForUser(user)
        render (view: "userHome", model: [user: user, followersList: followersList])
    }

    /**
     * Show users followed by user
     * @return list of followed
     */
    def showFollowed() {
        def user = springSecurityService.currentUser
        def followedList = userDaoService.findAllFollowedForUser(user)
        render (view: "userHome", model: [user: user, followedList: followedList])
    }

    /**
     *
     * @param s
     * @return
     */
    def light(Status s) {
        lightService.lightAStatus(springSecurityService.currentUser, s)
        redirect action: 'userHome', controller: 'user', namespace: null
    }

    /**
     *
     * @param s
     * @return
     */
    def unlight(Status s) {
        lightService.unlightAStatus(springSecurityService.currentUser, s)
        redirect action: 'userHome', controller: 'user', namespace: null
    }

}
