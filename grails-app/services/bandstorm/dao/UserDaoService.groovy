package bandstorm.dao

import grails.transaction.Transactional
import bandstorm.User


/**
 * Dao for user
 */
@Transactional
class UserDaoService implements IGenericDao<User> {

    @Override
    User create(User user) {
        user.save()
        return user
    }

    @Override
    void delete(User user) {
        user.delete()
    }

    @Override
    User update(User user) {
        user.save()
        return user
    }
}

