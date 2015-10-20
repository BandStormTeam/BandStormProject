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

    /**
     * Get all users corresponding to keywords
     * @param keywords : a string input for the research
     * @param maxItemsForSearch : max item for pagination
     * @param page : value for the pagination
     * @return a Map containing the list of Users and the total number of result
     */
    Map getAllUsersByKeywords(String keywords,Integer maxItemsForSearch,Integer page){

        Integer max = maxItemsForSearch*page+maxItemsForSearch
        Integer offset =(maxItemsForSearch*page)

        def resultsList = User.createCriteria().list {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("username", "%" + keyword + "%")
                    ilike("firstName", "%" + keyword + "%")
                    ilike("lastName", "%" + keyword + "%")
                }
            }
            maxResults(max)
            firstResult(offset)
        }

        def resultsCount = User.createCriteria().count() {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("username", "%" + keyword + "%")
                    ilike("firstName", "%" + keyword + "%")
                    ilike("lastName", "%" + keyword + "%")
                }
            }
        }

        def result = new HashMap()
        result.totalOfUser = resultsCount.toInteger()
        result.userList = resultsList.toList()

        return result;
    }

}
