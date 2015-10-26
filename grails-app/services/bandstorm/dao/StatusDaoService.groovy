package bandstorm.dao

import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

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

        List<Status> statusList = new ArrayList<Status>()

        for(int i=1; i<=20; i++){
            def status = new Status(content: "My status "+i, lightCount: 0)
            statusList.add(status)
        }

        return statusList
    }
}
