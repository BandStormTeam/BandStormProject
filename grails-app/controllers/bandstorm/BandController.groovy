package bandstorm

import bandstorm.dao.BandDaoService
import bandstorm.dao.UserDaoService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

import javax.naming.AuthenticationException
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(["ROLE_USER", "ROLE_ADMIN"])
@Transactional(readOnly = true)
class BandController {

    BandDaoService bandDaoService
    UserDaoService userDaoService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        params.sort = "name"

        try {
            User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
            respond user.groupsFollowed.toList(), model: [bandInstanceCount: user.groupsFollowed.toList().size()]
        }
        catch (AuthenticationException) {
        }


    }

    def show(Band bandInstance) {
        respond bandInstance
    }

    def create() {
        respond new Band(params)
    }

    @Transactional
    def save(Band bandInstance) {
        if (bandInstance == null) {
            notFound()
            return
        }

        try {
            bandInstance.name = params.bandName
            bandInstance.address = params.bandAddress
            bandInstance.description = params.bandDescription
            bandInstance.validate()
        } catch (Exception e) {
            e.printStackTrace()
            render template: 'form', model: [bandInstance: bandInstance, status: "KO"]
        }

        if (bandInstance.hasErrors()) {
            render template: 'form', model: [bandInstance: bandInstance, status: "KO"]
            return
        }

        try {
            if (params.bandTags) {
                def paramTags = params.bandTags.tokenize(';')
                bandInstance.tags = new ArrayList<Tag>()
                paramTags.eachWithIndex { item, index ->
                    def tag = new Tag(name: item).save()
                    bandInstance.tags.add(tag)
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
        }

        try {
            User user = User.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
            user.addToGroupsFollowed(bandInstance)
            userDaoService.update(user)
        }
        catch (AuthenticationException) {
        }


        bandDaoService.create(bandInstance)
        render template: 'form', model: [bandInstance: bandInstance, status: "OK"]


    }

    def edit(Band bandInstance) {
        respond bandInstance
    }

    @Transactional
    def update(Band bandInstance) {
        if (bandInstance == null) {
            notFound()
            return
        }

        if (bandInstance.hasErrors()) {
            respond bandInstance.errors, view: 'edit'
            return
        }

        bandInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Band.label', default: 'Band'), bandInstance.id])
                redirect bandInstance
            }
            '*' { respond bandInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Band bandInstance) {

        if (bandInstance == null) {
            notFound()
            return
        }

        bandInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'band.label', default: 'Band'), bandInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'band.label', default: 'Band'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
