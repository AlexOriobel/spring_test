package com.test.spring_test.kafka.cunsumer;

import java.util.concurrent.CountDownLatch;

import com.test.spring_test.Status;
import com.test.spring_test.dao.CashBackRepository;
import com.test.spring_test.model.CashBack;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Consumer {
    private final CashBackRepository cashBackRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.json}")
    public void receive(CashBack cashBack) {
        cashBackRepository.save(cashBack);
        cashBack.setMoney(cashBack.getOrderS().getCost() * 0.2);
        cashBackRepository.save(cashBack);

    }
}