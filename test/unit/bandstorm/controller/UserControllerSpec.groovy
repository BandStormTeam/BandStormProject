package bandstorm.controller

import bandstorm.*
import bandstorm.service.LightService
import bandstorm.service.StatusService
import bandstorm.service.UserService
import bandstorm.service.dao.BandDaoService
import bandstorm.service.dao.UserDaoService
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import spock.lang.Specification

@TestFor(UserController)
@Mock(User)
class UserControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["username"] = "toto"
        params["firstName"] = "titi"
        params["lastName"] = "tata"
        params["email"] = "toto@mail.com"
        params["country"] = "France"
        params["password"] = "123456"
        def calendar = Calendar.getInstance()
        calendar.set(1991,GregorianCalendar.SEPTEMBER,20)
        params["birthDate"] = calendar.getTime()
        params["urlAvatar"] = "http://toto.com"
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The redirection is correct"
        response.redirectedUrl == '/user/home'
    }


    void "Test the searchBand action returns a band list"() {

        given: "BandDaoService exist"
        List bandList = new ArrayList<Band>()
        bandList.push(new Band())

        Map searchResult = new HashMap()
        searchResult.bandList = bandList
        searchResult.bandsCount = 10

        controller.bandDaoService = Mock(BandDaoService) {
            getAllBandsByKeywords(_,_,_) >> searchResult
        }

        when: "The searchBand action is executed"
        controller.searchBand("Bob",10,0)

        then: "The bandList is correct and keywords too"
        model.bandList
        model.keywords == "Bob"

    }

    void "Test the searchUser action returns a user list"() {

        given: "UserService exist"
        List userList = new ArrayList<User>()
        userList.push(new User(firstName:"John"))

        Map searchResult = new HashMap()
        searchResult.userList = userList
        searchResult.userCount = 10

        controller.userDaoService = Mock(UserDaoService) {
            getAllUsersByKeywords(_,_,_) >> searchResult
        }

        when: "The searchUser action is executed"
        controller.searchUser("John",10,0)

        then: "The userList is correct and keywords too"
        model.userList
        model.keywords == "John"

    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.userInstance != null
    }


    void "Test the passwordSettings action returns the correct model"() {

        given: "The security service for user is created"
        populateValidParams(params)
        User user = new User(params)
        user.save()
        controller.userService = Mock(UserService)
        controller.userService.springSecurityService >> Mock(SpringSecurityService) {
            getCurrentUser() >> user
        }

        when: "The passwordSettings action is executed without a parameter"
        controller.passwordSettings()

        then: "The model is correctly created"
        model.userInstance == user

        when: "the passwordSettings action is executed with a parameter"
        controller.passwordSettings(user)

        then: "the returned user is the one passed as a parameter"
        model.userInstance == user
    }


    void "Test the profilSettings action returns the correct model"() {

        given: "The security service for user is created"
        populateValidParams(params)
        User user = new User(params)
        user.save()
        controller.userService = Mock(UserService)
        controller.userService.springSecurityService >> Mock(SpringSecurityService) {
            getCurrentUser() >> user
        }

        when: "The profilSettings action is executed"
        controller.profilSettings(user)

        then: "The model is correctly created"
        model.userInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        given: "The service dao for user is created"
        populateValidParams(params)
        User user = new User(params)
        user.save()
        controller.userDaoService = Mock(UserDaoService) {
            create(_) >> user
        }
        controller.userService = Mock(UserService) {
            setUserRole(_) >> true
        }

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def userBad = new User()
        userBad.validate()
        controller.save(userBad)

        then: "The create view is rendered again with the correct model"
        model.userInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()

        def userGood = new User(params)

        controller.save(userGood)

        then: "the success creation view is rendred"
        model.type == 'success'
        view == '/user/successCreation'
        User.count() == 1
    }

    void "test save method with null parameter"() {
        when:"we try to save a null object"
        controller.save(null)

        then: "the response value is not found"
        response.status == HttpStatus.NOT_FOUND.value()
    }

    void "test save method with null parameter with form content type"() {

        when: "we try to save a null object through a form"
        request.contentType = FORM_CONTENT_TYPE
        controller.save(null)

        then: "the response status is notfound and the redirect is to the index page"
        response.redirectedUrl == '/user/index'
        flash.message != null
    }

    void "test profile settings"() {

        given: "used entities"
        User user = new User(params)
        controller.userService = Mock(UserService)
        controller.userService.springSecurityService >> Mock(SpringSecurityService) {
            getCurrentUser() >> user
        }

        when: "we have a null object passed as a parameter"
        controller.passwordSettings(null)

        then: "the method returns the currently logged user"
        model.userInstance == user

        when: "we call the profile settings method with the created user"
        controller.profilSettings(user)

        then: "the response is the same created user"
        model.userInstance == user
    }

    void "test logout method"() {

        given :"the userService"
        controller.userService = Mock(UserService) {
            logout(_,_) >> true
        }

        when: "we call the logout method"
        controller.logout()

        then: "we are redirected to the main page"
        response.redirectedUrl == "/"
    }

    void "Test that the show action returns the correct model"() {
        given:"current user"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()

        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def user = new User(params)
        // Mock the service to avoid NullPointer…
        controller.statusService = Mock(StatusService) {
            show(User) >> user
        }

        controller.show(user)

        then: "A model is populated containing the domain instance"
        model.userInstance == user
    }

    void "Test the update action performs an update on a valid domain instance"() {

        given: "The service dao for user is created"
        populateValidParams(params)
        User user = new User(params)
        user.save()
        controller.userDaoService = Mock(UserDaoService) {
            update(_) >> user
        }

        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null,"profilSettings")

        then: "A 404 error is returned"
        response.redirectedUrl == '/user/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def userBad = new User()
        userBad.validate()
        controller.update(userBad,"profilSettings")

        then: "The edit view is rendered again with the invalid instance"
        view == 'profilSettings'
        model.userInstance == userBad
    }

    void "update method test for a valid instance"() {

        when: "A valid domain instance is passed to the update action"
        populateValidParams(params)
        def userBad = new User(params).save(flush: true)
        controller.userDaoService = Mock(UserDaoService) {
            update(_) >> userBad
        }
        controller.update(userBad,"profilSettings")

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/user/profilSettings"
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/user/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def user = new User(params).save(flush: true)

        then: "It exists"
        User.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(user)

        then: "The instance is deleted"
        User.count() == 0
        response.redirectedUrl == '/user/index'
        flash.message != null
    }

    void "test home method"() {
        given: "a valid user instance"
        def user = new User(params)

        controller.userDaoService = Mock(UserDaoService) {
            create(_) >> user
        }
        controller.userService = Mock(UserService)
        controller.userService.authenticationManager = Mock(AuthenticationManager)
        controller.userService.springSecurityService >> Mock(SpringSecurityService)
        populateValidParams(params)


        when: "the home method is called with a user without a role"
        controller.home()

        then: "the authentification fails so the user is not redirected to the home view"
        view != 'home'
    }

    void "test url redirect method when user is logged in"() {
        given: "the user service"
        controller.userService = Mock(UserService)

        when: "the urlRedirect method is called"
        controller.userService.springSecurityService >> Mock(SpringSecurityService) {
            isLoggedIn() >> true
        }
        controller.urlRedirect()

        then: "the home action is called"
        response.redirectedUrl == '/user/home'

    }

    void "test url redirect method when user is not logged in"() {
        given: "the user service"
        controller.userService = Mock(UserService)

        when: "no user is logged in"
        controller.userService.springSecurityService >> Mock(SpringSecurityService) {
            isLoggedIn() >> false
        }
        controller.urlRedirect()

        then: "the user is redirected to the index page"
        response.redirectedUrl == '/index'
    }

    void "test showFollowers method"(){
        given:"current user"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        controller.userDaoService = Mock(UserDaoService)
        controller.userDaoService.findAllFollowersForUser(_) >> []

        when: "showFollowers method is use"
        controller.showFollowers()

        then: "we get good view and model"
        view == "/user/home"
        model.followersList != null
    }

    void "test showFollowed method"(){
        given:"current user"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        controller.userDaoService = Mock(UserDaoService)
        controller.userDaoService.findAllFollowedForUser(_) >> []

        when: "showFollowers method is use"
        controller.showFollowed()

        then: "we get good view and model"
        view == "/user/home"
        model.followedList != null
    }

    void "test followUser method"() {
        given:"the user to follow and current user"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        User user = Mock(User)
        controller.userDaoService = Mock(UserDaoService)
        controller.userDaoService.followUser(_,_) >> new Follow()

        when:"followUser method is use"
        controller.followUser(user)

        then:"we get the good redirect"
        response.redirectedUrl == "/user/show"
    }

    void "test unfollowUser method"() {
        given:"the user to unfollow and current user"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        User user = Mock(User)
        controller.userDaoService = Mock(UserDaoService)

        when:"unfollowUser method is use"
        controller.unfollowUser(user)

        then:"we get the good redirect"
        response.redirectedUrl == "/user/show"
    }

    void "test the lighting method"() {
        given: "The user and the status to light"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        Status s = Mock(Status)
        controller.lightService = Mock(LightService)

        when: "Light the status"
        controller.light(s)

        then:"We have the good redirection"
        response.redirectedUrl == "/user/home"
    }

    void "test the unlighting method"() {
        given: "The user and the status to light"
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> new User()
        Status s = Mock(Status)
        controller.lightService = Mock(LightService)

        when: "Light the status"
        controller.unlight(s)

        then:"We have the good redirection"
        response.redirectedUrl == "/user/home"
    }

}
