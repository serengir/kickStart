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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.myproject.domain.Car;
import com.myproject.services.CarService;
import org.primefaces.model.chart.*;

@ManagedBean
@RequestScoped
public class ChartView implements Serializable {

    private LineChartModel lineModel;
    private LineChartModel lineModel2;
    private List<Car> cars;

    @ManagedProperty("#{carService}")
    private CarService service;

    @ManagedProperty("#{passingValueBean}")
    private PassingValueBean passingValue;

    @ManagedProperty("#{dtFilterView}")
    private FilterView dtFilterView;

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

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
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

    public FilterView getDtFilterView() {
        return dtFilterView;
    }

    public void setDtFilterView(FilterView dtFilterView) {
        this.dtFilterView = dtFilterView;
    }

    private void createLineModels() {
        lineModel = initCategoryModel();
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Price");
        yAxis.setMin(0);
        yAxis.setMax(100000);
        yAxis = lineModel.getAxis(AxisType.X);
        yAxis.setLabel("Production year");
        yAxis.setMin(1960);
        yAxis.setMax(2014);

        lineModel2 = initCategoryModel2();
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.setSeriesColors("000000, FFFFFF, 00FF00, FF0000, 0000FF, FFA500, C6C6C6, FFFF00, 8B4513, 800000");
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Price");
        yAxis.setMin(0);
        yAxis.setMax(100000);
        yAxis = lineModel2.getAxis(AxisType.X);
        yAxis.setLabel("Production year");
        yAxis.setMin(1960);
        yAxis.setMax(2014);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        //cars = passingValue.getOldCarList();
        cars = dtFilterView.getCars();
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
            model.addSeries(carSeries[i]);
        }
        return model;
    }

    private LineChartModel initCategoryModel2() {
        LineChartModel model = new LineChartModel();
        //cars = passingValue.getOldCarList();
        cars = dtFilterView.getCars();
        Collections.sort(cars);
        ChartSeries[] carSeries = new ChartSeries[service.getColors().size()];
        for(int i=0;i<service.getColors().size();i++){
            carSeries[i]= new ChartSeries();
            carSeries[i].setLabel(service.getColors().get(i));
            for (Car car : cars) {
                if(car.getColor().equals(carSeries[i].getLabel())){
                    carSeries[i].set(car.getYear(),car.getPrice());
                }
            }
            model.addSeries(carSeries[i]);
        }
        return model;
    }

}