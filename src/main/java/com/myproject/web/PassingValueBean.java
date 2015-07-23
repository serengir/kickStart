package com.myproject.web;

import com.myproject.domain.Car;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="passingValueBean")
@SessionScoped
public class PassingValueBean implements Serializable{
    private int beanie;
    private List<Car> oldCarList;

    public List<Car> getOldCarList() {
        return oldCarList;
    }

    public void setOldCarList(List<Car> oldCarList) {
        this.oldCarList = oldCarList;
    }

    public int getBeanie() {
        return beanie;
    }

    public void setBeanie(int beanie) {
        this.beanie = beanie;
    }
}
