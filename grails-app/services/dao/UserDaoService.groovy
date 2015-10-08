package dao

import bandstorm.User

/**
 * Dao for user
 */
class UserDaoService implements IGenericDao<User> {

    @Override
    void create(User user) {
        user.save
    }

    @Override
    void delete(User user) {
        user.delete()
    }

    @Override
    void update(User user) {
        user.save
    }
}
