package bandstorm.service.dao

import bandstorm.Event
import grails.transaction.Transactional

/**
 * Dao service for events
 */
@Transactional
class EventDAOService implements IGenericDao<Event> {


    @Override
    Event create(Event event) {
        event.save()
    }

    @Override
    void delete(Event event) {
        event.delete()
    }

    @Override
    Event update(Event event) {
        event.save()
    }
}
