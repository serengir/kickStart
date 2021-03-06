package com.myproject.web;

import com.myproject.domain.Car;
import com.myproject.model.CarModel;
import com.myproject.services.CarModelService;
import com.myproject.services.CarModelServiceImpl;
import com.myproject.services.CarService;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.hibernate.cfg.Configuration;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ManagedBean(name = "dtFilterView")
@SessionScoped
public class FilterView implements Serializable {

    private List<Car> cars;

    private List<Car> filteredCars;

    @ManagedProperty("#{carService}")
    private CarService service;

    @ManagedProperty("#{passingValueBean}")
    private PassingValueBean passingValue;


    @PostConstruct
    public void init() {
        if(passingValue.getOldCarList()!=null){
            cars = passingValue.getOldCarList();
        }else{
            cars = service.createCars(20);
            passingValue.setOldCarList(cars);
        }
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("nature" + i + ".jpg");
        }
    }

    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

    public List<String> getBrands() {
        return service.getBrands();
    }

    public List<String> getColors() {
        return service.getColors();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<Car> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public PassingValueBean getPassingValue() {
        return this.passingValue;
    }

    public void setPassingValue(PassingValueBean passingValue) {
        this.passingValue = passingValue;
    }

    public int sumPrices() {
        int result = 0;
        for (Car car : cars) {
            result += car.getPrice();
        }
        return result;
    }

    public int sumFilterPrices() {
        int result = 0;
        if (filteredCars != null) {
            for (Car car : filteredCars) {
                result += car.getPrice();
            }
        }
        if (result > 0) {
            passingValue.setBeanie(result);
        } else {
            passingValue.setBeanie(sumPrices());
        }
        return result;
    }

    public List<String> completeBrand(String query) {
        List<String> allBrands = service.getBrands();
        List<String> filteredTexts = new ArrayList<String>();
        query = query.toLowerCase();
        for (int i = 0; i < allBrands.size(); i++) {
            String skin = allBrands.get(i);
            if (skin.toLowerCase().startsWith(query)) {
                filteredTexts.add(skin);
            }
        }
        return filteredTexts;
    }

    public List<String> completeColor(String query) {
        List<String> allColors = service.getColors();
        List<String> filteredTexts = new ArrayList<String>();
        query = query.toLowerCase();
        for (int i = 0; i < allColors.size(); i++) {
            String skin = allColors.get(i);
            if (skin.toLowerCase().startsWith(query)) {
                filteredTexts.add(skin);
            }
        }
        return filteredTexts;
    }

    public String getNewView(String whereTo){
        return "go_"+whereTo;
    }

    private void addNewCar(String brand, String year, String color, String price, boolean sold){
        String generatedId=service.getRandomId();
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/database-context.xml");

        CarModel cm = new CarModel();
        cm.setCarid(generatedId);
        cm.setBrand(brand);
        cm.setYear(Integer.valueOf(year));
        cm.setColor(color);
        cm.setPrice(Integer.valueOf(price));
        if(sold)cm.setSold("true");
        else cm.setSold("false");

        CarModelService emService = context.getBean("carModelService", CarModelService.class);
        emService.persistCarModel(cm);
        context.close();
        cars = passingValue.getOldCarList();
        cars.add(new Car(generatedId,brand,Integer.valueOf(year),color,Integer.valueOf(price),sold));
        passingValue.setOldCarList(cars);
    }
    public void addNewCar(MaskView mask){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sakses!","Z powodzeniem dodano nowego wpisa."));
        addNewCar(mask.getBrand(), mask.getYear(), mask.getColor(), mask.getPrice(), mask.isSold());
    }

    public void deleteCar(Car target) {
        cars = passingValue.getOldCarList();
        cars.remove(target);
        if(filteredCars!=null)filteredCars.remove(target);
        passingValue.setOldCarList(cars);
    }

    public void testData(MaskView mask){
        RequestContext.getCurrentInstance().execute("PF('dlg').show()");
    }

    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public String getColorCode(String color){
        return service.getColorCodes().get(color);
    }
}