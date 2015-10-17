package bandstorm.service

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
}
