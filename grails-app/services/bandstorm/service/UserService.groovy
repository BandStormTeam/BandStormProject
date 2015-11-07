package bandstorm.service

import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.User
import bandstorm.service.dao.StatusDaoService
import bandstorm.service.dao.UserDaoService
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Service for user
 */
@Transactional
class UserService {
    def springSecurityService
    AuthenticationManager authenticationManager
    def logoutHandlers
    def mailService
    StatusDaoService statusDaoService
    UserDaoService userDaoService

    /**
     * Log an user
     * @param username : name of the user
     * @param password : password of the user
     * @throws AuthenticationException : probleme at the connexion
     */
    def logIn(String username, String password) throws AuthenticationException {
        Authentication newAuthentification = new UsernamePasswordAuthenticationToken(username, password)
        Authentication result = authenticationManager.authenticate(newAuthentification)
        SecurityContextHolder.getContext().setAuthentication(result)
    }

    /**
     * Logout an user
     * @param request : request of context
     * @param response : response of context
     */
    def logout(request, response) {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth) {
            logoutHandlers.each  { handler->
                handler.logout(request,response,auth)
            }
        }
    }

    /**
     * Define the role of an user
     * @param userInstance : user to set
     */
    def setUserRole(User userInstance) {
        SecRole userRole = SecRole.findByAuthority('ROLE_USER')
        SecUserSecRole.create userInstance, userRole, true
    }

    /**
     * Send an email to an user
     * @param email : email of the user
     * @param username : name of the user
     * @param url : url for redirection
     * @return mail is send
     */
    def contactUser(String email, String username, String url) {
        mailService.sendMail {
            to email
            subject "Account validation"
            html view: "/email/validation", model: [username: username, redirectUrl: url]

        }
    }
}
