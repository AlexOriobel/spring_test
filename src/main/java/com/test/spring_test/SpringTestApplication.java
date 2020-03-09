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
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "Docker + Spring Boot");
        model.addAttribute("msg", "Welcome to the docker container!");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        addTestOrder();
    }

    private void addTestOrder() {

        Client client = new Client();
        client.setPhone("Sevchik");
        client.setName("Vasa");
        client.setEmail("Pupkin");
        clienOreder.addNewClient(client);

        OrderS orderS = new OrderS();
        orderS.setData("20.20.20");
        orderS.setOrders("Зубные палочки");
        orderS.setCost(900000.0);
        orderS.setClientOfOrders(client);
        clienOreder.addNewOrder(orderS);

    }
}
