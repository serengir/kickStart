package com.myproject.model;

import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name = "CARS")
public class CarModel {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "CARID", nullable = false)
    private String carid;
    @Column(name = "BRAND", nullable = false)
    private String brand;
    @Column(name = "YEAR", nullable = false)
    private int year;
    @Column(name = "COLOR", nullable = false)
    private String color;
    @Column(name = "PRICE", nullable = false)
    private int price;
    @Column(name = "SOLD", nullable = false)
    private String sold;

    public CarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }
}