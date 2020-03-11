package com.test.spring_test.kafka.producer;

import com.test.spring_test.dto.CashBackDto;
import com.test.spring_test.model.CashBack;
import com.test.spring_test.model.OrderS;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Producer {

	@Value("${spring.kafka.topic.microservic}")
	private String mcCashBack;

	@Autowired
	private KafkaTemplate<String, CashBackDto> kafkaTemplate;

	public void sendMessage(CashBackDto cashBack) {

		ListenableFuture<SendResult<String, CashBackDto>> future =
				kafkaTemplate.send(mcCashBack, cashBack);

		future.addCallback(new ListenableFutureCallback<SendResult<String, CashBackDto>>() {

			@Override
			public void onSuccess(SendResult<String, CashBackDto> stringCashBackSendResult) {
				log.info("sending massages={},{},{}", "to topic", cashBack, mcCashBack);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Unable to send massage = [" + cashBack + "] due to" + ex.getMessage());
			}
		});
	}
}
