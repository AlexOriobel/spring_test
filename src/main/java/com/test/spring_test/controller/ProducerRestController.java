package com.test.spring_test.controller;

import com.test.spring_test.Status;
import com.test.spring_test.dao.ClientRepository;
import com.test.spring_test.dao.OrdersRepository;
import com.test.spring_test.dao.ProccesResultRepository;
import com.test.spring_test.dto.ProcessResult;
import com.test.spring_test.kafka.producer.Producer;
import com.test.spring_test.model.CashBack;
import com.test.spring_test.model.OrderS;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class ProducerRestController {

    private final Producer producer;

    @Autowired
    ProducerRestController(Producer producer) {
        this.producer = producer;
    }

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProccesResultRepository proccesResultRepository;

    @GetMapping("/cashback/calc")
    public ResponseEntity<ProcessResult> calc(@RequestParam(value = "userId") Long clientId,
                                              @RequestParam(value = "orderId") Long orderId) {
//
        CashBack cashBack = new CashBack();
        ProcessResult processResult = new ProcessResult();
        cashBack.setClient(clientRepository.findById(clientId).get());
        cashBack.setOrderS(ordersRepository.findById(orderId).get());
        processResult.setStatus(Status.IN_PROGRESS);
        processResult.setCashBack(cashBack);
        proccesResultRepository.save(processResult);
        this.producer.sendMessage(cashBack);
        return new ResponseEntity<>(cashBack.getProcessResult(), HttpStatus.OK);

    }

    @GetMapping("/cashback/check/{id}")
    public ResponseEntity<ProcessResult> check(@RequestParam(value = "executionalId:") Long resultId) {
        ProcessResult processResult = proccesResultRepository.findById(resultId).get();
        if (processResult.getCashBack().getMoney() > 0
                & processResult.getCashBack().getOrderS().getCost() > 0) {

            processResult.setStatus(Status.SUCCESS);

        } else {
            processResult.setStatus(Status.ERROR);
        }
        proccesResultRepository.save(processResult);
        return new ResponseEntity<>(processResult, HttpStatus.OK);

    }


}
