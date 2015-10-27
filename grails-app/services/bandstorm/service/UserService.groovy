package bandstorm.service

import bandstorm.SecRole
import bandstorm.SecUserSecRole
import bandstorm.Status
import bandstorm.User
import bandstorm.dao.StatusDaoService
import bandstorm.dao.UserDaoService
import grails.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class UserService {
    def springSecurityService
    AuthenticationManager authenticationManager
    def logoutHandlers
    def mailService
    StatusDaoService statusDaoService
    UserDaoService userDaoService

    def logIn(String username, String password) throws AuthenticationException {
        Authentication newAuthentification = new UsernamePasswordAuthenticationToken(username, password)
        Authentication result = authenticationManager.authenticate(newAuthentification)
        SecurityContextHolder.getContext().setAuthentication(result)
    }

    def logout(request, response) {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth) {
            logoutHandlers.each  { handler->
                handler.logout(request,response,auth)
            }
        }
    }

    def setUserRole(User userInstance) {
        SecRole userRole = SecRole.findByAuthority('ROLE_USER')
        SecUserSecRole.create userInstance, userRole, true
    }

    def contactUser(String email, String username, String url) {
        mailService.sendMail {
            to email
            subject "Account validation"
            html view: "/email/validation", model: [username: username, redirectUrl: url]

        }
    }
}
