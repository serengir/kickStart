package com.myproject.services;

import com.myproject.model.CarModel;

import java.util.List;

public interface CarModelService {
    void persistCarModel(CarModel carModel);

    CarModel findCarModelById(int id);

    List<CarModel> getAllCarModels();

    void updateCarModel(CarModel carModel);

    void deleteCarModel(CarModel carModel);

    void deleteAllCarModels();
}
