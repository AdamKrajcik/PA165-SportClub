package DAO;

import Entities.Entity;

/**
 * @author Jan Cech
 * Generic interface that every entity specific DAO should implement
 */
//so far only for basic CRUD operations, no further filtering possible
//ToDo maybe find a better name for this
public interface Dao<E extends Entity> {
    /**
     * Creates a new entity in db
     * @param entity entity to be created
     * @return id of created entity
     */
    long create(E entity);

    /**
     * Updates entity in db
     * @param entity entity to be updated
     * @return true if update was successful, false otherwise
     */
    boolean update(E entity);

    /**
     * Deletes entity from database
     * @param entity entity to be deleted
     * @return true if delete was successful, false otherwise
     */
    boolean delete(E entity);

    /**
     * Select entity with given id from database
     * @param id id of entity
     * @return entity with given id, null if there is none
     */
    E select(long id);

}
