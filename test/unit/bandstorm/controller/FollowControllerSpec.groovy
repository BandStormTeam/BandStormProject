package bandstorm.controller

import bandstorm.Follow
import bandstorm.FollowController
import bandstorm.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FollowController)
@Mock(Follow)
class FollowControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["follower"] = Mock(User)
        params["followed"] = Mock(User)
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.followInstanceList
        model.followInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.followInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def follow = new Follow()
        follow.validate()
        controller.save(follow)

        then: "The create view is rendered again with the correct model"
        model.followInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        follow = new Follow(params)

        controller.save(follow)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/follow/show/1'
        controller.flash.message != null
        Follow.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def follow = new Follow(params)
        controller.show(follow)

        then: "A model is populated containing the domain instance"
        model.followInstance == follow
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def follow = new Follow(params)
        controller.edit(follow)

        then: "A model is populated containing the domain instance"
        model.followInstance == follow
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/follow/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def follow = new Follow()
        follow.validate()
        controller.update(follow)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.followInstance == follow

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        follow = new Follow(params).save(flush: true)
        controller.update(follow)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/follow/show/$follow.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/follow/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def follow = new Follow(params).save(flush: true)

        then: "It exists"
        Follow.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(follow)

        then: "The instance is deleted"
        Follow.count() == 0
        response.redirectedUrl == '/follow/index'
        flash.message != null
    }
}
