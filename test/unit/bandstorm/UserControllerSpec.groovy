package bandstorm

import bandstorm.dao.BandDaoService
import bandstorm.service.UserService
import grails.plugin.springsecurity.SpringSecurityService
import bandstorm.dao.UserDaoService
import grails.test.mixin.*
import org.springframework.security.authentication.AuthenticationManager
import spock.lang.*

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
        params["birthDate"] = Mock(Date)
        params["urlAvatar"] = "http://toto.com"
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.userInstanceList
        model.userInstanceCount == 0
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

        when: "The passwordSettings action is executed"
        controller.passwordSettings()

        then: "The model is correctly created"
        model.userInstance != null
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

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        userBad = new User(params).save(flush: true)
        controller.update(userBad,"profilSettings")

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/user/index"
        flash.message != null
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

    void "test userHome method"() {
        given: "a valid user instance"
        def user = new User(params)

        controller.userDaoService = Mock(UserDaoService) {
            create(_) >> user
        }
        controller.userService = Mock(UserService)
        controller.userService.authenticationManager = Mock(AuthenticationManager)
        controller.userService.springSecurityService >> Mock(SpringSecurityService)
        populateValidParams(params)


        when: "the userHome method is called with a user without a role"
        controller.userHome()

        then: "the authentification fails so the user is not redirected to the userHome view"
        view != 'userHome'

    }
}
