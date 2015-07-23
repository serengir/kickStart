package com.myproject.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.myproject.model.CarModel;

import java.util.List;

@Repository("employeeDAO")
public class CarDAOImpl implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void persistCarModel(CarModel carModel){
        sessionFactory.getCurrentSession().persist(carModel);
        System.out.println("ok");
    }

    public CarModel findCarModelById(int id){
        return (CarModel)sessionFactory.getCurrentSession().get(CarModel.class, id);
    }

    public List<CarModel> getAllCarModels(){
        return sessionFactory.getCurrentSession().createQuery("from CarModel").list();
    }

    public void updateCarModel(CarModel carModel){
        sessionFactory.getCurrentSession().update(carModel);
    }

    public void deleteCarModel(CarModel carModel){
        sessionFactory.getCurrentSession().delete(carModel);
    }

    public void deleteAllCarModels() {sessionFactory.getCurrentSession().createQuery("delete CarModel").executeUpdate();}
}