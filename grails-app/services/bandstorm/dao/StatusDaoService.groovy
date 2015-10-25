package bandstorm.dao

import bandstorm.Status
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
}
