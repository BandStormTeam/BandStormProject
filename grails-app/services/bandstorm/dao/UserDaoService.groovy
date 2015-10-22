package bandstorm.dao

import bandstorm.Follow
import bandstorm.User
import grails.transaction.Transactional

@Transactional
class UserDaoService implements IGenericDao<User> {

    @Override
    User create(User user) {
        user.save()
    }

    @Override
    void delete(User user) {
        user.delete()
    }

    @Override
    User update(User user) {
        user.save()
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
}
