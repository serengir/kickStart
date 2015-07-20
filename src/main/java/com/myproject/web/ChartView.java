package com.myproject.web;

/**
 * Created by Marek on 2015-07-20.
 */
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.myproject.domain.Car;
import com.myproject.services.CarService;
import org.primefaces.model.chart.*;

@ManagedBean
@SessionScoped
public class ChartView implements Serializable {

    private LineChartModel lineModel;
    private List<Car> cars;

    @ManagedProperty("#{carService}")
    private CarService service;

    @ManagedProperty("#{passingValueBean}")
    private PassingValueBean passingValue;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public CarService getService() {
        return service;
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public PassingValueBean getPassingValue() {
        return passingValue;
    }

    public void setPassingValue(PassingValueBean passingValue) {
        this.passingValue = passingValue;
    }

    private void createLineModels() {


        lineModel = initCategoryModel();
        lineModel.setTitle("Summary Chart");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Price"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Year");
        yAxis.setMin(0);
        yAxis.setMax(100000);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        cars = passingValue.getOldCarList();
        Collections.sort(cars);

        ChartSeries[] carSeries = new ChartSeries[service.getBrands().size()];
        for(int i=0;i<service.getBrands().size();i++){
            carSeries[i]= new ChartSeries();
            carSeries[i].setLabel(service.getBrands().get(i));

            for (Car car : cars) {
                if(car.getBrand().equals(carSeries[i].getLabel())){
                    carSeries[i].set(car.getYear(),car.getPrice());
                }
            }
            carSeries[i].set(0, 0);
            model.addSeries(carSeries[i]);
        }

        return model;
    }

}