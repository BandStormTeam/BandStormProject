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

    /**
     * Get all events corresponding to keywords
     * @param keywords : a string input for the research
     * @param maxItemsForSearch : max item for pagination
     * @param page : value for the pagination
     * @return a Map containing the list of events and the total number of result
     */
    Map getAllEventsByKeywords(String keywords, Integer maxItemsForSearch, Integer page) {
        Integer max = maxItemsForSearch * page + maxItemsForSearch
        Integer offset = (maxItemsForSearch * page)

        def resultsList = Event.createCriteria().list {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("name", "%" + keyword + "%")
                    ilike("description", "%" + keyword + "%")
                }
            }
            maxResults(max)
            firstResult(offset)
        }

        def result = new HashMap()
        result.eventList = resultsList.toList()
        result.eventCount = result.eventList.size()

        return result;
    }
}
