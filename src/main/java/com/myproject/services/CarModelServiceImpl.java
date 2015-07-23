package com.myproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myproject.DAO.CarDAO;
import com.myproject.model.CarModel;

import java.util.List;

@Service("carModelService")
public class CarModelServiceImpl implements CarModelService{

    @Autowired
    CarDAO carDAO;

    @Transactional
    public void persistCarModel(CarModel carModel) {
        carDAO.persistCarModel(carModel);
    }

    @Transactional
    public void updateCarModel(CarModel carModel) {
        carDAO.updateCarModel(carModel);
    }

    @Transactional
    public CarModel findCarModelById(int id) {
        return  carDAO.findCarModelById(id);
    }

    @Transactional
    public void deleteCarModel(CarModel carModel){
        carDAO.deleteCarModel(carModel);
    }

    @Transactional
    public List<CarModel> getAllCarModels(){
        return carDAO.getAllCarModels();
    }

    @Transactional
    public void deleteAllCarModels() {carDAO.deleteAllCarModels();}
}
