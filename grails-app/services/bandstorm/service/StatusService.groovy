package bandstorm.service

import bandstorm.Status
import grails.transaction.Transactional

@Transactional
class StatusService {

    List<Status> getStatusForTimeline(){
        def criteria = Status.createCriteria()
        List<Status> res = criteria.list {
            order('dateCreated','desc')
        }
    }
}
