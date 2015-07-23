package com.myproject.web;

import com.myproject.domain.Car;
import com.myproject.model.CarModel;
import com.myproject.services.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Marek on 2015-07-23.
 */
@Component
public class CarModelActions {


    @Qualifier("carModelService")
    @Autowired
    private CarModelService emService;

    public void exportCar(FilterView view){
        emService.deleteAllCarModels();
        CarModel cm;
        for (Car car : view.getCars()) {
            cm = new CarModel();
            cm.setCarid(car.getId());
            cm.setBrand(car.getBrand());
            cm.setYear(car.getYear());
            cm.setColor(car.getColor());
            cm.setPrice(car.getPrice());
            if(car.isSold())cm.setSold("true");
            else cm.setSold("false");
            emService.persistCarModel(cm);
        }
    }

    public void importCars(FilterView view) {
        List<CarModel> cmodels = emService.getAllCarModels();
        List<Car> ccars = new ArrayList<Car>();
        for (CarModel cm : cmodels) {
            ccars.add(new Car(cm.getCarid(), cm.getBrand(), cm.getYear(), cm.getColor(), cm.getPrice(), cm.getSold().equals("true")));
        }
        view.setCars(ccars);
    }
}
