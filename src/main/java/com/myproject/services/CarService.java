package com.myproject.services;

import com.myproject.domain.Car;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;

@ManagedBean(name = "carService")
@ApplicationScoped
public class CarService {

    private final static String[] colors;

    private final static String[] brands;

    private final static HashMap<String,String> colorCodes;

    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";

        colorCodes = new HashMap<>();
        colorCodes.put("Black","#000000");
        colorCodes.put("White","#FFFFFF");
        colorCodes.put("Green","#00FF00");
        colorCodes.put("Red","#FF0000");
        colorCodes.put("Blue","#0000FF");
        colorCodes.put("Orange","#FFA500");
        colorCodes.put("Silver","#C6C6C6");
        colorCodes.put("Yellow","#FFFF00");
        colorCodes.put("Brown","#8B4513");
        colorCodes.put("Maroon","#800000");

        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
    }

    public List<Car> createCars(int size) {
        List<Car> list = new ArrayList<Car>();
        for (int i = 0; i < size; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
        }

        return list;
    }

    public String getRandomId() {
        String result="#"+((int) (Math.random() * 799999)+100000);
        result = result.replaceAll("0","A");
        result = result.replaceAll("1","B");
        result = result.replaceAll("2","C");
        result = result.replaceAll("3","D");
        result = result.replaceAll("4","E");
        result = result.replaceAll("5","F");
        return result;
    }

    public static HashMap<String, String> getColorCodes() {
        return colorCodes;
    }

    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }

    private String getRandomColor() {
        return colors[(int) (Math.random() * 10)];
    }

    private String getRandomBrand() {
        return brands[(int) (Math.random() * 10)];
    }

    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }

    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true : false;
    }

    public List<String> getColors() {
        return Arrays.asList(colors);
    }

    public List<String> getBrands() {
        return Arrays.asList(brands);
    }
}