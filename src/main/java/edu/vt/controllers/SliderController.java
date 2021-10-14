/*
 * Created by Sai Venkat Banda on 2021.10.02
 * Copyright © 2021 Sai Venkat Banda. All rights reserved.
 */

package edu.vt.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("sliderController")
@RequestScoped
public class SliderController {

  // The List contains image filenames, e.g., photo1.png, photo2.png, etc.
  private List<String> listOfSliderImageFilenames;

  /*
  The PostConstruct annotation is used on a method that needs to be executed after
  dependency injection is done to perform any initialization. The initialization
  method init() is the first method invoked before this class is put into service.
  */
  @PostConstruct
  public void init() {
    listOfSliderImageFilenames = new ArrayList<>();

    for (int i = 1; i <= 12; i++) {
      listOfSliderImageFilenames.add("photo" + i + ".png");
    }
  }

  /*
  =============
  Getter Method
  =============
   */
  public List<String> getListOfSliderImageFilenames() {
    return listOfSliderImageFilenames;
  }

  /*
  ===============
  Instance Method
  ===============
  */
  public String description(String imageFilename) {

    String imageDescription = "";

    switch (imageFilename) {
      case "photo1.png":
        imageDescription = "Tesla Model X SUV";
        break;
      case "photo2.png":
        imageDescription = "BMW X5 SUV";
        break;
      case "photo3.png":
        imageDescription = "Honda Civic Type R";
        break;
      case "photo4.png":
        imageDescription = "Chevy Camaro ZL1";
        break;
      case "photo5.png":
        imageDescription = "Jeep Grand Cherokee Altitude";
        break;
      case "photo6.png":
        imageDescription = "Ford Focus SE";
        break;
      case "photo7.png":
        imageDescription = "Subaru Crosstrek";
        break;
      case "photo8.png":
        imageDescription = "Toyota RAV4 Hybrid";
        break;
      case "photo9.png":
        imageDescription = "Volkswagen Jetta";
        break;
      case "photo10.png":
        imageDescription = " Nissan 370Z";
        break;
      case "photo11.png":
        imageDescription = "Mercedes-Benz E-Class";
        break;
      case "photo12.png":
        imageDescription = "Lexus RX 350L";
        break;
    }

    return imageDescription;
  }
}
