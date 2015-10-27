package bandstorm.dao

import bandstorm.Event
import grails.transaction.Transactional

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
