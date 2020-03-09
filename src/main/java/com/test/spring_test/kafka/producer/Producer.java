package com.test.spring_test.kafka.producer;

import com.test.spring_test.model.CashBack;
import com.test.spring_test.model.OrderS;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Value("${spring.kafka.topic.json}")
    private String jsonTopic;
    @Autowired
    private KafkaTemplate<String, CashBack> kafkaTemplate;
    public void sendMessage(CashBack cashBack) {

        ListenableFuture<SendResult<String, CashBack>> future =
                kafkaTemplate.send(jsonTopic, cashBack );

        future.addCallback(new ListenableFutureCallback<SendResult<String,CashBack>>() {

            @Override
            public void onSuccess(SendResult<String, CashBack> result) {
                System.out.println("Massage send successed");;
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Massage failure ");
            }
        });
    }
}
