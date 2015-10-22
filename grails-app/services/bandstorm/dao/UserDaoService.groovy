package bandstorm.dao

import bandstorm.User
import grails.transaction.Transactional

@Transactional
class UserDaoService implements IGenericDao<User> {
    @Override
    User create(User user) {
        user.save()
    }

    @Override
    void delete(User user) {
        user.delete()
    }

    @Override
    User update(User user) {
        user.save(flush: true)
    }

    /**
     * Get all users corresponding to keywords
     * @param keywords : a string input for the research
     * @param maxItemsForSearch : max item for pagination
     * @param page : value for the pagination
     * @return a Map containing the list of Users and the total number of result
     */
    Map getAllUsersByKeywords(String keywords,Integer maxItemsForSearch,Integer page){

        Integer max = maxItemsForSearch*page+maxItemsForSearch
        Integer offset =(maxItemsForSearch*page)

        def resultsList = User.createCriteria().list {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("username", "%" + keyword + "%")
                    ilike("firstName", "%" + keyword + "%")
                    ilike("lastName", "%" + keyword + "%")
                }
            }
            maxResults(max)
            firstResult(offset)
        }

        def resultsCount = User.createCriteria().count() {
            or {
                keywords.split(" ").each { keyword ->
                    ilike("username", "%" + keyword + "%")
                    ilike("firstName", "%" + keyword + "%")
                    ilike("lastName", "%" + keyword + "%")
                }
            }
        }

        def result = new HashMap()
        result.totalOfUser = resultsCount.toInteger()
        result.userList = resultsList.toList()

        return result;
    }
}
