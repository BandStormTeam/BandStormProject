package bandstorm.service

import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

@Transactional
class StatusService {

    List<Status> getStatusForTimeline(){
        def criteria = Status.createCriteria()
        List<Status> res = criteria.list {
            order('dateCreated','desc')
        }
    }

    List<Status> getStatusOfUser(User u) {
        Status.createCriteria().list {
            eq("author.id", u.id)
            order('dateCreated','desc')
        }
    }
}
