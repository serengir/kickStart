package com.myproject.services;

import com.myproject.model.CarModel;
public interface CarModelService {
    void persistCarModel(CarModel carModel);

    CarModel findCarModelById(int id);

    void updateCarModel(CarModel carModel);

    void deleteCarModel(CarModel carModel);
}
