package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;

import java.util.List;

public interface MonsterDao {
    /**
     * Get a list of all the monster names (ascending order) in the datastore.
     * The list is never null. It is empty if there are no city names in the datastore.
     *
     * @return all the monster names as a list of Strings.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    List<String> getAllMonsters();

    /**
     * Get a randomly selected monster from the datastore.
     * If there are no cities in the datastore, return null.
     *
     * @return the fully populated Monster object randomly selected.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    Monster getRandomMonster();

    /**
     * Get a list of monsters (unordered) from the datastore who's name is like the given name.
     * The list is never null. It is empty if there are no cities for the given state abbreviation
     *      in the datastore.
     *
     * @param monsterName The name of like monsters to get from the datastore
     * @return a list of City objects.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    List<Monster> getMonstersByName(String monsterName);

    /**
     * Add a new monster to the datastore based upon the given Monster object.
     * The given Monster object does not need to be fully populated, only the properties required by
     *      the target datastore.
     *
     * @param monster The Monster object to add to the datastore.
     * @return a fully populated Monster object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    Monster createMonster(Monster monster);

    /**
     * Retrieve a Monster from the datatstore who's type is like the given parameter type.
     * @param type The name of type of monsters to get from the datastore
     * @return list of Monster objects by the given type.
     * @throws DaoException if an error occurs.
     */

    List<Monster> getMonsterByType(String type);

}
