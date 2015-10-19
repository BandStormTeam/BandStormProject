package bandstorm.service

import bandstorm.Follow
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

    /**
     * Allow an user to follow an other
     * @param follower User who want to follow
     * @param followed User followed
     * @return the follow between the users
     */
    Follow followUser(User follower, User followed){
        Follow myFollow = new Follow(followed: followed,follower: follower)
        myFollow.save()
        myFollow
    }

    /**
     * Allow an user to unfollow an other
     * @param follower User who want to unfollow
     * @param followed User to unfollow
     * @return
     */
    def unfollowUser(User follower, User followed){
        Follow myFollow = Follow.findByFollowerAndFollowed(follower,followed)
        myFollow.delete()
    }
}
