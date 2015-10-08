package bandstorm.dao

import bandstorm.Status
import grails.transaction.Transactional

@Transactional
class StatusDaoService implements IGenericDao<Status> {

    @Override
    Status create(Status status) {
        status.save()
    }

    @Override
    void delete(Status status) {
        status.delete()
    }

    @Override
    Status update(Status status) {
        status.save()
    }
}
