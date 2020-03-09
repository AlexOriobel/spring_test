package com.test.spring_test.controller;

import com.test.spring_test.dao.OrdersRepository;
import com.test.spring_test.model.OrderS;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrdersRestController {

    private final OrdersRepository ordersRepository;

    @GetMapping // Get orders list
    public ResponseEntity<Iterable<OrderS>> getOrdersList() {
        return new ResponseEntity<>(ordersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}") // Get orders by id
    public ResponseEntity<OrderS> getOrdersId(@PathVariable Long id) {
        return new ResponseEntity<>(ordersRepository.findById(id).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @PostMapping // Add orders
    public ResponseEntity<OrderS> creatFoodPrice(@RequestBody OrderS orderS) {
        OrderS saveOrders = ordersRepository.save(orderS);
        return new ResponseEntity<>(saveOrders, HttpStatus.CREATED);
    }

    @PutMapping("{id}") // Update food price list
    public ResponseEntity<OrderS> update(@PathVariable Long id, OrderS orderS) {
        orderS.setId(id);
        OrderS saveOrders = ordersRepository.save(orderS);
        return new ResponseEntity<>(saveOrders, HttpStatus.OK);
    }
}
