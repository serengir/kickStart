package com.myproject.services;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.myproject.model.CarModel;

public class TestService {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/database-context.xml");

        CarModel cm = new CarModel();
        cm.setCarid("#669966");
        cm.setBrand("John");
        cm.setYear(1980);
        cm.setColor("Pink");
        cm.setPrice(10000);
        cm.setSold("true");

        CarModelService emService = context.getBean("carModelService", CarModelService.class);
        emService.persistCarModel(cm);
        context.close();
    }

}