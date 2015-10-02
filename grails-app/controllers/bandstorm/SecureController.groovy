package bandstorm
import grails.plugin.springsecurity.annotation.Secured

class SecureController {

    @Secured('ROLE_ADMIN')
    def index() {
        render 'Secure access only'
    }

    def logout() {
        render 'testing logout function'
    }
}
