package bandstorm.service

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
    AuthenticationManager authenticationManager
    def logoutHandlers
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
            logoutHandlers.each { handler ->
                handler.logout(request, response, auth)
            }
        }
    }

    def addStatusToUser(User user, Status status) {
        user.addToPosts(status)
        statusDaoService.create(status)
        userDaoService.update(user)
    }

    def getAllUsersByKeywords(String keywords,Integer page){
        List<User> userList = new ArrayList<User>();

        return userList;
    }

}
