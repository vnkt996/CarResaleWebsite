/*
 * Created by Sai Venkat Banda on 2021.10.02
 * Copyright © 2021 Sai Venkat Banda. All rights reserved.
 */

package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class CarFacade extends AbstractFacade<Car> {
  /*
    ---------------------------------------------------------------------------------------------
    The EntityManager is an API that enables database CRUD (Create Read Update Delete) operations
    and complex database searches. An EntityManager instance is created to manage entities
    that are defined by a persistence unit. The @PersistenceContext annotation below associates
    the entityManager instance with the persistence unitName identified below.
    ---------------------------------------------------------------------------------------------
     */
  @PersistenceContext(unitName = "Cars-BandaPU")
  private EntityManager entityManager;

  // Obtain the object reference of the EntityManager instance in charge of
  // managing the entities in the persistence context identified above.
  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  /*
  This constructor method invokes its parent AbstractFacade's constructor method,
  which in turn initializes its entity class type T and entityClass instance variable.
   */
  public CarFacade() {
    super(Car.class);
  }

  /*
    ***********************************************************************************
    *   Java Persistence API (JPA) Query Formulation for Searching a MySQL Database   *
    ***********************************************************************************
    By default, MySQL does not distinguish between upper and lower case letters in searches.
    Therefore, searches based on the queries below are all case insensitive by default.

    The LIKE Expression
        SELECT c FROM car c WHERE c.name LIKE :'in%'       All countries whose names begin with "in"
        SELECT c FROM car c WHERE c.name LIKE :'%tan'      All countries whose names end with "tan"
        SELECT c FROM car c WHERE c.name LIKE :'%ge%'      All countries whose names contain "ge"

    The LIKE expression uses wildcard character % to search for strings that match the wildcard pattern.

    ================================
    EntityManager Method createQuery
    ================================
    Query createQuery(String qlString)
        Create an instance of Query for executing a Java Persistence (JPA) query language statement.
    Parameter:
        qlString - a Java Persistence query string, e.g., "SELECT c FROM car c WHERE c.name LIKE :searchString"
    Returns:
        the object reference of the newly created Query object

    =========================
    Query Method setParameter
    =========================
    Query setParameter(String name, Object value)
        Bind an argument value to a named parameter
    Parameters:
        name - parameter name (e.g., "searchString")
        value - parameter value (e.g., the searchString parameter that contains the search string the user entered for searching)
    Returns:
        the same object reference of the newly created Query object

    ==========================
    Query Method getResultList
    ==========================
    List getResultList()
        Execute a SELECT query and return the query results as an untyped List
    Returns:
        the object reference of the newly created List containing the search results
    */

  /*
   ***************************
   *   Search Query Type 1   *
   ***************************
   */

  /*
  -----------------------------
  Search Category: CAR MAKE
  -----------------------------
   */
  // cars where car make contains the searchString.
  public List<Car> makeQuery(String searchString) {
    // Place the % wildcard before and after the search string to search for it anywhere in the make name
    searchString = "%" + searchString + "%";
    // Conduct the search in a case-insensitive manner and return the results in a list.
    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.make LIKE :searchString")
            .setParameter("searchString", searchString)
            .getResultList();
  }

  /*
  -----------------------------
  Search Category: CAR model
  -----------------------------
   */
  // cars where car model contains the searchString.
  public List<Car> modelQuery(String searchString) {
    // Place the % wildcard before and after the search string to search for it anywhere in the model name
    searchString = "%" + searchString + "%";
    // Conduct the search in a case-insensitive manner and return the results in a list.
    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.model LIKE :searchString")
            .setParameter("searchString", searchString)
            .getResultList();
  }

  /*
  -----------------------------
  Search Category: CAR engineType
  -----------------------------
   */
  // cars where car engineType contains the searchString.
  public List<Car> engineTypeQuery(String searchString) {
    // Place the % wildcard before and after the search string to search for it anywhere in the engineType name
    searchString = "%" + searchString + "%";
    // Conduct the search in a case-insensitive manner and return the results in a list.
    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.engineType LIKE :searchString")
            .setParameter("searchString", searchString)
            .getResultList();
  }

  /*
  -----------------------------
  Search Category: CAR driveType
  -----------------------------
   */
  // cars where car driveType contains the searchString.
  public List<Car> driveTypeQuery(String searchString) {
    // Place the % wildcard before and after the search string to search for it anywhere in the driveType name
    searchString = "%" + searchString + "%";
    // Conduct the search in a case-insensitive manner and return the results in a list.
    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.driveType LIKE :searchString")
            .setParameter("searchString", searchString)
            .getResultList();
  }

  /*
  -----------------------------
  Search Category: CAR all
  -----------------------------
   */
  // cars where car driveType contains the searchString.
  public List<Car> allQuery(String searchString) {
    // Place the % wildcard before and after the search string to search for it anywhere in the driveType name
    searchString = "%" + searchString + "%";
    // Conduct the search in a case-insensitive manner and return the results in a list.
    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.driveType LIKE :searchString OR c.engineType LIKE :searchString OR c.model LIKE :searchString OR c.make LIKE :searchString")
            .setParameter("searchString", searchString)
            .getResultList();
  }


  /*
   ***************************
   *   Search Query Type 2   *
   ***************************
   */
  // year >= min yearQ and year <= max yearQ
  public List<Car> type2SearchQuery(Integer minYearQ, Integer maxYearQ) {

    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.year >= :minYearQ AND c.year <= :maxYearQ")
            .setParameter("minYearQ", minYearQ)
            .setParameter("maxYearQ", maxYearQ)
            .getResultList();
  }

  /*
   ***************************
   *   Search Query Type 3   *
   ***************************
   */
  // Mileage >= minMileageQ and Mileage <= maxMileageQ
  public List<Car> type3SearchQuery(Integer minMileageQ, Integer maxMileageQ) {

    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.mileage >= :minMileageQ AND c.mileage <= :maxMileageQ")
            .setParameter("minMileageQ", minMileageQ)
            .setParameter("maxMileageQ", maxMileageQ)
            .getResultList();
  }

  /*
   ***************************
   *   Search Query Type 4  *
   ***************************
   */
  // year >= minYearQ and mileage <= maxMileageQ
  public List<Car> type4SearchQuery(Integer minYearQ, Integer maxMileageQ) {

    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.year >= :minYearQ AND c.mileage <= :maxMileageQ")
            .setParameter("minYearQ", minYearQ)
            .setParameter("maxMileageQ", maxMileageQ)
            .getResultList();
  }

  /*
   ***************************
   *   Search Query Type 5  *
   ***************************
   */
  // year >= minYearQ and cityMPG >= maxMileageQ and mileage <= maxMileageQ and mileage >= minMileageQ
  public List<Car> type5SearchQuery(Integer minYearQ, Integer minCityMPGQ, Integer minMileageQ, Integer maxMileageQ) {

    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.year >= :minYearQ AND c.cityMPG >= :minCityMPGQ AND c.mileage >= :minMileageQ AND c.mileage <= :maxMileageQ")
            .setParameter("minYearQ", minYearQ)
            .setParameter("minCityMPGQ", minCityMPGQ)
            .setParameter("minMileageQ", minMileageQ)
            .setParameter("maxMileageQ", maxMileageQ)
            .getResultList();
  }

  /*
   ***************************
   *   Search Query Type 6  *
   ***************************
   */
  //  year >= minYearQ and year <= maxYearQ and cityMPG >= maxMileageQ and mileage <= maxMileageQ and driveType contanis driveTypeContainsQ
  public List<Car> type6SearchQuery(Integer maxMileageQ, Integer minCityMPGQ, String driveTypeContainsQ, Integer minYearQ, Integer maxYearQ) {

    driveTypeContainsQ = "%" + driveTypeContainsQ + "%";

    return getEntityManager().createQuery(
                    "SELECT c FROM Car c WHERE c.mileage <= :maxMileageQ AND c.cityMPG >= :minCityMPGQ AND c.driveType LIKE :driveTypeContainsQ AND c.year >= :minYearQ AND c.year <= :maxYearQ")
            .setParameter("maxMileageQ", maxMileageQ)
            .setParameter("minCityMPGQ", minCityMPGQ)
            .setParameter("driveTypeContainsQ", driveTypeContainsQ)
            .setParameter("minYearQ", minYearQ)
            .setParameter("maxYearQ", maxYearQ)
            .getResultList();
  }

}
