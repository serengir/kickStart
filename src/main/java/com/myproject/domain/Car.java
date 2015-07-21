package com.myproject.domain;

import java.io.Serializable;

/**
 * Created by Marek on 2015-07-14.
 */
public class Car implements Serializable, Comparable<Car>{
    private int id;
    private String brand;
    private int year;
    private String color;
    private int price;
    private boolean sold;

    public Car(int id, String brand, int year, String color, int price, boolean sold) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.sold = sold;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (price != car.price) return false;
        if (sold != car.sold) return false;
        if (id!=car.id) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        return !(color != null ? !color.equals(car.color) : car.color != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + year;
        result = 31 * result + color.hashCode();
        result = 31 * result + price;
        result = 31 * result + (sold ? 1 : 0);
        return result;
    }

    public int compareTo(Car other){
        return year-other.year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                '}';
    }
}
