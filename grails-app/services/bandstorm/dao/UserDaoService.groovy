package bandstorm.dao

import bandstorm.Follow
import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional


/**
 * Dao for user
 */
@Transactional
class UserDaoService implements IGenericDao<User> {

    @Override
    User create(User user) {
        user.save()
        return user
    }

    @Override
    void delete(User user) {
        user.delete()
    }

    @Override
    User update(User user) {
        user.save(flush:true)
        return user
    }

    /**
     * Allow an user to follow an other
     * @param follower User who want to follow
     * @param followed User followed
     * @return the follow between the users
     */
    Follow followUser(User follower, User followed){
        Follow myFollow = new Follow(followed: followed,follower: follower)
        myFollow.save(flush: true)
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
        myFollow.delete(flush: true)
    }

    /**
     * Find a follow between two users if exist
     * @param follower user follower
     * @param followed user followed
     * @return a follow if found else null
     */
    Follow findFollowByFollowerAndFollowed(User follower, User followed){
        Follow result = Follow.findByFollowerAndFollowed(follower,followed)
        result
    }

    /**
     * Find a list of followers for an user
     * @param myUser the given user
     * @return a user list
     */
    List<User> findAllFollowersForUser(User myUser){
        List<User> followers = []
        def followList = Follow.findAllByFollowed(myUser)
        followList.each {
            followers.add(it.follower)
        }
        followers
    }

    /**
     * Find a list of followed for an user
     * @param myUser the given user
     * @return a user list
     */
    List<User> findAllFollowedForUser(User myUser){
        List<User> followed = []
        def followList = Follow.findAllByFollower(myUser)
        followList.each {
            followed.add(it.followed)
        }
        followed
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

    /**
     * Add a status to the list of an user's status
     * @param user : a user whom we add the status
     * @param status : the status to be added
     */
    def addStatusToUser(User user, Status status) {
        user.addToPosts(status)
        user.save(flush:true)
    }

}
