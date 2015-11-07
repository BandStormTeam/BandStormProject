package bandstorm

import bandstorm.service.dao.BandDaoService
import bandstorm.service.dao.UserDaoService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

/**
 * Controller class for Bands
 */
@Secured(["ROLE_USER", "ROLE_ADMIN"])
@Transactional(readOnly = true)
class BandController {
    BandDaoService bandDaoService
    UserDaoService userDaoService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    AuthenticationManager authenticationManager

    /**
     * Show the list of the followed bands for current user.
     * @param max : max of displayed band in the page
     * @return List of bands
     */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "dateCreated"
        params.order = "desc"
        def calendar = Calendar.getInstance()
        calendar.set(2015,Calendar.SEPTEMBER, 01)
        Band band = new Band(name: "", description: "", address: "")
        respond Band.list(params), model: [bandInstance: band,bandInstanceCount: Band.count()]
    }

    /**
     * Show details for the selected band
     * @param bandInstance : selected band
     * @return band details
     */
    def show(Band bandInstance) {
        if(bandInstance == null) {
            return response.sendError(404)
        }

        respond bandInstance
    }

    /**
     * Create a band from the form
     * @return band instance
     */
    def create() {
        respond new Band(params)
    }

    /**
     * Save band from the form
     * @param bandInstance : selected band
     * @return band instance
     */
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

    /**
     * Edit a band instance
     * @param bandInstance: selected band
     * @return a band instance
     */
    def edit(Band bandInstance) {
        respond bandInstance
    }

    /**
     * Update a band instance
     * @param bandInstance: band instance to update
     * @return band instance
     */
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

    /**
     * Delete a band
     * @param bandInstance : band to delete
     * @return if the band is deleted
     */
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

    /**
     * Show an error page, if not found.
     */
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
