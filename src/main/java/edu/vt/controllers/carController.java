/*
 * Created by Sai Venkat Banda on 2021.10.02
 * Copyright © 2021 Sai Venkat Banda. All rights reserved.
 */

package edu.vt.controllers;

import edu.vt.EntityBeans.Car;
import edu.vt.FacadeBeans.CarFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;

/*
---------------------------------------------------------------------------
The @Named (javax.inject.Named) annotation indicates that the objects
instantiated from this class will be managed by the Contexts and Dependency
Injection (CDI) container. The name "carController" is used within
Expression Language (EL) expressions in JSF (XHTML) facelets pages to
access the properties and invoke methods of this class.
---------------------------------------------------------------------------
 */
@Named("carController")

/*
The @SessionScoped annotation preserves the values of the carController
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/*
-----------------------------------------------------------------------------
Marking the carController class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized.

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer,
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
-----------------------------------------------------------------------------
 */
public class carController implements Serializable {

  /*
    ===============================
    Instance Variables (Properties)
    ===============================
    */

  /*
  The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
  carFacade bean into the instance variable 'carFacade' after it is instantiated at runtime.
   */
  @EJB
  private CarFacade carFacade;

  // List of object references of car objects
  private List<Car> listOfCars = null;

  // selected = object reference of a selected car object
  private Car selected;

  // Flag indicating if car data changed or not
  private Boolean carDataChanged;

  // searchItems = List of object references of car objects found in the search
  private List<Car> searchItems = null;

  // search type is between 1 to 6
  private Integer searchType;

  // searchField refers to searching car make, model, name, capitalCity, language, or currency individually or search in each
  private String searchField;

  // searchString contains the character string the user entered for searching the selected searchField
  private String searchString;

  // logoFileName
  private String logoFileName;

  // Search Query Variables (Q = Query)
  private String makeQ;
  private String modelQ;
  private String engineTypeQ;
  private String driveTypeQ;
  private Integer maxYearQ;
  private Integer maxMileageQ;
  private Integer minYearQ;
  private Integer minMileageQ;
  private Integer minCityMPGQ;

  /*
    =========================
    Getter and Setter Methods
    =========================
  */
  public List<Car> getListOfCars() {
    if (listOfCars == null) {
      listOfCars = carFacade.findAll();
    }
    return listOfCars;
  }

  public CarFacade getCarFacade() {
    return carFacade;
  }

  public void setCarFacade(CarFacade carFacade) {
    this.carFacade = carFacade;
  }

  public void setListOfCars(List<Car> listOfCars) {
    this.listOfCars = listOfCars;
  }

  public Car getSelected() {
    return selected;
  }

  public void setSelected(Car selected) {
    this.selected = selected;
  }

  public Boolean getCarDataChanged() {
    return carDataChanged;
  }

  public void setCarDataChanged(Boolean carDataChanged) {
    this.carDataChanged = carDataChanged;
  }

  public void setSearchItems(List<Car> searchItems) {
    this.searchItems = searchItems;
  }

  public String getSearchField() {
    return searchField;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public String getSearchString() {
    return searchString;
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString;
  }

  public String getMakeQ() {
    return makeQ;
  }

  public void setMakeQ(String makeQ) {
    this.makeQ = makeQ;
  }

  public String getModelQ() {
    return modelQ;
  }

  public void setModelQ(String modelQ) {
    this.modelQ = modelQ;
  }

  public String getEngineTypeQ() {
    return engineTypeQ;
  }

  public void setEngineTypeQ(String engineTypeQ) {
    this.engineTypeQ = engineTypeQ;
  }

  public String getDriveTypeQ() {
    return driveTypeQ;
  }

  public void setDriveTypeQ(String driveTypeQ) {
    this.driveTypeQ = driveTypeQ;
  }

  public Integer getMaxYearQ() {
    return maxYearQ;
  }

  public void setMaxYearQ(Integer maxYearQ) {
    this.maxYearQ = maxYearQ;
  }

  public Integer getMaxMileageQ() {
    return maxMileageQ;
  }

  public void setMaxMileageQ(Integer maxMileageQ) {
    this.maxMileageQ = maxMileageQ;
  }

  public Integer getMinYearQ() {
    return minYearQ;
  }

  public void setMinYearQ(Integer minYearQ) {
    this.minYearQ = minYearQ;
  }

  public Integer getMinMileageQ() {
    return minMileageQ;
  }

  public void setMinMileageQ(Integer minMileageQ) {
    this.minMileageQ = minMileageQ;
  }

  public Integer getMinCityMPGQ() {
    return minCityMPGQ;
  }

  public void setMinCityMPGQ(Integer minCityMPGQ) {
    this.minCityMPGQ = minCityMPGQ;
  }

  public String getLogoFilename() {
    return logoFileName;
  }

  public void setLogoFilename(String logoFileName) {
    this.logoFileName = logoFileName;
  }

  /*
    ================
    Instance Methods
    ================
  */

  public Map<String, String> getGetCarLogoNamesAsMap() {
    Map<String,String> logoNames = new HashMap<>();
    List<String> logoNameList = Constants.CAR_LOGO_NAMES;
    for (String logoName: logoNameList)
      logoNames.put(logoName,logoName);
    return logoNames;
  }

  // Return list of logo names
  public List<String> getLogoNames() {
    return Constants.CAR_LOGO_NAMES;
  }


  /*
   ****************************************
   *   Unselect Selected Car Object   *
   ****************************************
   */
  public void unselect() {
    selected = null;
  }

  /*
   *************************************
   *   Cancel and Display List.xhtml   *
   *************************************
   */
  public String cancel() {
    // Unselect previously selected car object if any
    selected = null;
    return "/car/List?faces-redirect=true";
  }

  /*
   ******************************************
   *   Display the Search Results JSF Page  *
   ******************************************
   */
  public String search(Integer type) {
    // Set search type given as input parameter
    searchType = type;

    // Unselect previously selected car if any before showing the search results
    selected = null;

    // Invalidate list of search items to trigger re-query.
    searchItems = null;

    return "/databaseSearch/databaseSearchResults?faces-redirect=true";
  }

  /*
   ****************************************************************************************************
   *   Return the list of object references of all those cars that satisfy the search criteria   *
   ****************************************************************************************************
   */
  // This is the Getter method for the instance variable searchItems
  public List<Car> getSearchItems() {
        /*
        =============================================================================================
        You must construct and return the search results List "searchItems" ONLY IF the List is null.
        Any List provided to p:dataTable to display must be returned ONLY IF the List is null
        ===> in order for the column-sort to work. <===
        =============================================================================================
         */
    if (searchItems == null) {
      switch (searchType) {
        case 1: // Search Type 1
          switch (searchField) {
            case "Make":
              // Return the list of object references of all those countries where
              // car name contains the searchString entered by the user.
              searchItems = carFacade.makeQuery(searchString);
              break;
            case "Model":
              // Return the list of object references of all those countries where
              // capital city name contains the searchString entered by the user.
              searchItems = carFacade.modelQuery(searchString);
              break;
            case "Engine Type":
              // Return the list of object references of all those countries where
              // language name contains the searchString entered by the user.
              searchItems = carFacade.engineTypeQuery(searchString);
              break;
            case "Drive Type":
              // Return the list of object references of all those countries where
              // currency name contains the searchString entered by the user.
              searchItems = carFacade.driveTypeQuery(searchString);
              break;
            default:
              // Return the list of object references of all those countries where car name OR
              // capital city name OR language name OR currency name contains the searchString entered by the user.
              searchItems = carFacade.allQuery(searchString);
          }
          break;
        case 2: // Search Type 2
          // car population is between minPopulationQ and maxPopulationQ
          searchItems = carFacade.type2SearchQuery(minYearQ, maxYearQ);
          break;
        case 3: // Search Type 3
          // car totalArea is between minTotalAreaQ and maxTotalAreaQ
          searchItems = carFacade.type3SearchQuery(minMileageQ, maxMileageQ);
          break;
        case 4: // Search Type 4
          // car name contains nameQ AND population ≥ populationQ
          searchItems = carFacade.type4SearchQuery(minYearQ, maxMileageQ);
          break;
        case 5: // Search Type 5
          // car currency contains currencyQ AND totalArea ≤ totalAreaQ
          searchItems = carFacade.type5SearchQuery(minYearQ, minCityMPGQ, minMileageQ, maxMileageQ);
          break;
        case 6: // Search Type 6
          // car language contains languageQ AND population is between minPopulationQ and maxPopulationQ
          searchItems = carFacade.type6SearchQuery(maxMileageQ, minCityMPGQ, driveTypeQ, minYearQ, maxYearQ);
          break;
        default:
          Methods.showMessage("Fatal Error", "Search Type is Out of Range!", "");
      }
    }
    return searchItems;
  }

  /*
   ***************************************
   *   Prepare to Create a New Car   *
   ***************************************
   */
  public void prepareCreate() {
        /*
        Instantiate a new Car object and store its object reference into
        instance variable 'selected'. The Car class is defined in Car.java
         */
    selected = new Car();
  }

  /*
   *************************************************
   *   DELETE Selected Car from the Database   *
   *************************************************
   */
  public void getDestroy() {
    Methods.preserveMessages();
        /*
         The object reference of the car to be deleted is stored in the instance variable 'selected'
         See the persist method below.
         */
    persist(PersistAction.DELETE, "Car was Successfully Deleted!");

    if (!JsfUtil.isValidationFailed()) {
      // No JSF validation error. The DELETE operation is successfully performed.
      selected = null;            // Remove selection
      listOfCars = null;     // Invalidate list of cars to trigger re-query.
      carDataChanged = true;
    }
  }

  /*
   **********************************************************************************************
   *   Perform CREATE, UPDATE (EDIT), and DELETE (DESTROY, REMOVE) Operations in the Database   *
   **********************************************************************************************
   */
  /**
   * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
   * @param successMessage displayed to inform the user about the result
   */
  private void persist(PersistAction persistAction, String successMessage) {
    if (selected != null) {
      try {
        if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     CarFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
          carFacade.edit(selected);
        } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove(selected) method performs the DELETE operation of the "selected"
                     object in the database.

                     CarFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
          carFacade.remove(selected);
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, "A persistence error occurred!");
        }
      } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, "A persistence error occurred");
      }
    }
  }

  public boolean validate() {
    //--------------------------------------
    // Validate Car Make and Car Model
    //--------------------------------------

    String carMake = selected.getMake().toLowerCase();

    if (!Constants.CAR_LOGO_NAMES.contains(carMake)) {
      Methods.showMessage("Error",
              "Car manufacturer is not recognized!", "");
      return false;
    }

    // check if car make and car logo match
    if(!selected.getLogoFileName().toLowerCase().equals(selected.getMake().toLowerCase())){
      Methods.showMessage("Error",
              "Car manufacturer company is not same as car logo file name!", "");
      return false;
    }

    return true;
  }
  /*
   ********************************************
   *   CREATE a New Car in the Database   *
   ********************************************
   */
  public void create() {
    Methods.preserveMessages();

    if( !this.validate()){
      return;
    }

    // Validation is successful to create the new car
    String x = selected.getMake();
    selected.setMake( x.substring(0,1).toUpperCase() + x.substring(1));
        /*
        An enum is a special Java type used to define a group of constants.
        The constants CREATE, DELETE and UPDATE are defined as follows in JsfUtil.java

                public enum PersistAction {
                    CREATE,
                    DELETE,
                    UPDATE
                }
         */

        /*
         The object reference of the car to be created is stored in the instance variable 'selected'
         See the persist method below.
         */
    persist(PersistAction.CREATE, "Car was Successfully Created!");

    if (!JsfUtil.isValidationFailed()) {
      // No JSF validation error. The CREATE operation is successfully performed.
      selected = null;            // Remove selection
      listOfCars = null;     //
      carDataChanged = true;
    }
  }

  /*
   ***********************************************
   *   UPDATE Selected Car in the Database   *
   ***********************************************
   */
  public void update() {
    if( !this.validate()){
      return;
    }

    Methods.preserveMessages();


        /*
         The object reference of the car to be updated is stored in the instance variable 'selected'
         See the persist method below.
         */
    persist(PersistAction.UPDATE, "Car was Successfully Updated!");

    if (!JsfUtil.isValidationFailed()) {
      // No JSF validation error. The UPDATE operation is successfully performed.
      selected = null;        // Remove selection
      listOfCars = null;
      carDataChanged = true;
    }
  }

  /*
   *************************************************
   *   DELETE Selected Car from the Database   *
   *************************************************
   */
  public void destroy() {
    Methods.preserveMessages();
        /*
         The object reference of the car to be deleted is stored in the instance variable 'selected'
         See the persist method below.
         */
    persist(PersistAction.DELETE, "Car was Successfully Deleted!");

    if (!JsfUtil.isValidationFailed()) {
      // No JSF validation error. The DELETE operation is successfully performed.
      selected = null;            // Remove selection
      listOfCars = null;
      carDataChanged = true;
    }
  }
}
