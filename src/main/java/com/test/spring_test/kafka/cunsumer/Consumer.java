package com.test.spring_test.kafka.cunsumer;

import com.test.spring_test.dao.CashBackRepository;
import com.test.spring_test.dao.MoneyRepository;
import com.test.spring_test.dao.ProccesResultRepository;
import com.test.spring_test.dto.CashBackDto;
import com.test.spring_test.enumList.STATUS;
import com.test.spring_test.model.CashBack;
import com.test.spring_test.model.Money;
import com.test.spring_test.model.ProcessResult;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Consumer {

	private final CashBackRepository cashBackRepository;
	private final ProccesResultRepository proccesResultRepository;
	private final MoneyRepository moneyRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.microservic}")
	public void receive(CashBackDto cashBackDto) {
		ModelMapper modelMapper = new ModelMapper();
		CashBack cashBack = modelMapper.map(cashBackDto, CashBack.class);
		Money money = new Money();

		if (cashBack.getOrderS()
					.getCost() > 0) {
			cashBack.setMoney(cashBack.getOrderS()
									  .getCost() * 0.02);
			money.setMoney(cashBack.getMoney());
			money.setPercent(2.0);
			moneyRepository.save(money);
			cashBack.getProcessResult()
					.setStatus(STATUS.SUCCESS);
			cashBack.getProcessResult()
					.setMoney(money);
			proccesResultRepository.save(cashBack.getProcessResult());
			cashBackRepository.save(cashBack);
		} else {
			cashBack.getProcessResult()
					.setStatus(STATUS.ERROR);
			proccesResultRepository.save(cashBack.getProcessResult());
		}

	}
}