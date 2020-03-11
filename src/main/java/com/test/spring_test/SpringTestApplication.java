package com.test.spring_test;

import com.test.spring_test.model.*;
import com.test.spring_test.service.ClienOreder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringTestApplication {

    @Autowired
    private ClienOreder clienOreder;

    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        addTestOrder();
    }

    private void addTestOrder() {

        Client clienti = new Client();
        clienti.setPhone("Sevchik");
        clienti.setName("Vasa");
        clienti.setEmail("Pupkin");
        clienOreder.addNewClient(clienti);

        OrderS orderSi = new OrderS();
        orderSi.setData("20.20.20");
        orderSi.setOrd("Зубные палочки");
        orderSi.setCost(900000.0);
        orderSi.setClient(clienti);
        clienOreder.addNewOrder(orderSi);

    }
}
