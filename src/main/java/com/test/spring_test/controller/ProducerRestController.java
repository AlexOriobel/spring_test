package com.test.spring_test.controller;

import com.test.spring_test.dao.*;
import com.test.spring_test.dto.CashBackDto;
import com.test.spring_test.dto.ProcessResultDto;
import com.test.spring_test.enumList.STATUS;
import com.test.spring_test.model.ProcessResult;
import com.test.spring_test.kafka.producer.Producer;
import com.test.spring_test.model.CashBack;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProducerRestController {

	private final Producer producer;
	private final OrdersRepository ordersRepository;
	private final ClientRepository clientRepository;
	private final ProccesResultRepository proccesResultRepository;
	private final CashBackRepository cashBackRepository;
	private final MoneyRepository moneyRepository;


	@GetMapping("/cashback/calc")
	public ResponseEntity<ProcessResultDto> calc(@RequestParam(value = "userId") Long clientId,
												 @RequestParam(value = "orderId") Long orderId) {

		CashBack cashBack = new CashBack();
		ProcessResult processResult = new ProcessResult();
		processResult.setStatus(STATUS.IN_PROGRESS);
		proccesResultRepository.save(processResult);
		cashBack.setClient(clientRepository.findById(clientId)
										   .get());
		cashBack.setOrderS(ordersRepository.findById(orderId)
										   .get());
		cashBack.setProcessResult(processResult);
		cashBackRepository.save(cashBack);
		ModelMapper modelMapper = new ModelMapper();
		CashBackDto cashBackDto = modelMapper.map(cashBack, CashBackDto.class);
		producer.sendMessage(cashBackDto);
		ProcessResultDto processResultDto = modelMapper.map(processResult, ProcessResultDto.class);
		return new ResponseEntity<>(processResultDto, HttpStatus.OK);

	}

	@GetMapping("/cashback/check")
	public ResponseEntity<ProcessResult> check(@RequestParam(value = "executionalId:") Long resultId) {

		return new ResponseEntity<ProcessResult>(proccesResultRepository.findById(resultId)
																		.get(), HttpStatus.OK);

	}


}
