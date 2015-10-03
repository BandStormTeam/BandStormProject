package bandstorm

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

class SecureController {
    def springSecurityService

    @Secured('ROLE_ADMIN')
    def index() {
        render 'Secure access only'
    }

    @Secured('ROLE_ADMIN')
    def messager() {
        def user = springSecurityService.currentUser
        if(springSecurityService.isLoggedIn()) {
            render 'you\'re logged in '+ user
        } else {
            render 'testing logout function';
        }
    }

    @Secured('ROLE_ADMIN')
    def logout() {
        if(springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
        }
    }

}
