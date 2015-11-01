package bandstorm.service

import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

/**
 * Services for status
 */
@Transactional
class StatusService {

    /**
     * Get all status for connected user timeline
     * @return list of status
     */
    List<Status> getStatusForTimeline() {
        def criteria = Status.createCriteria()
        List<Status> res = criteria.list {
            order('dateCreated','desc')
        }
    }

    /**
     * Get all status for selected user
     * @param user : selected user
     * @return list of status
     */
    List<Status> getStatusOfUser(User user) {
        List<Status> res = Status.createCriteria().list {
            eq("author.id", user.id)
            order('dateCreated','desc')
        }
    }
}
