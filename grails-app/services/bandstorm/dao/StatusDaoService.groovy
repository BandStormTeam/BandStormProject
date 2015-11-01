package bandstorm.dao

import bandstorm.Follow
import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

/**
 * Dao service for status
 */
@Transactional
class StatusDaoService implements IGenericDao<Status> {

    @Override
    Status create(Status status) {
        status.save(flush: true)
    }

    @Override
    void delete(Status status) {
        status.delete(flush: true)
    }

    @Override
    Status update(Status status) {
        status.save(flush: true)
    }


    /**
     * Get last status of all user followed by the user
     * @param user : user who follow other user
     * @param page : page for the search
     * @return list of Status
     */
    List<Status> getLastFollowedStatusOfUser(User user,Integer page){

        Integer maxItemsForSearch = 10
        Integer offset =(maxItemsForSearch*page)

        List<Follow> followList = Follow.findAllByFollower(user)

        def resultsStatus = Status.createCriteria().list {

            or {
                followList.each { follow ->
                    eq("author",follow.followed)
                }
                eq("author",user)
            }

            order("dateCreated",'desc')

            maxResults(maxItemsForSearch)
            firstResult(offset)
        }

        List<Status> statusList = resultsStatus.toList()

        return statusList
    }
}
