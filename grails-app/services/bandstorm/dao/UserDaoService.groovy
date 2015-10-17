package bandstorm.dao

import bandstorm.User
import grails.transaction.Transactional

@Transactional
class UserDAOService implements IGenericDao<User> {

    def serviceMethod() {

    }

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
        user.save()
    }
}
