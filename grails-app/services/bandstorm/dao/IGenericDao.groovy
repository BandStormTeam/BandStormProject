package bandstorm.dao

/**
 * Interface for generic DAO
 */
interface IGenericDao<O> {

        /** Create an object */
        O create(O object);

        /** Delete an object */
        void delete(O object);

        /** Update an object */
        O update(O object);

}