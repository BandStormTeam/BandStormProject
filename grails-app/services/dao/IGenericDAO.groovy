package dao

/**
 * Interface for generic DAO
 */
interface IGenericDao<O> {

    /** Create an object */
    void create(O object);

    /** Delete an object */
    void delete(O object);

    /** Update an object */
    void update(O object);

}