package com.myproject.DAO;

import com.myproject.model.CarModel;

public interface CarDAO {
    void persistCarModel(CarModel carModel);

    CarModel findCarModelById(int id);

    void updateCarModel(CarModel carModel);

    void deleteCarModel(CarModel carModel);
}
