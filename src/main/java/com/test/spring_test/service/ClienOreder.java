package com.test.spring_test.service;

import com.test.spring_test.dao.*;
import com.test.spring_test.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienOreder {

    private final ClientRepository clientRepository;
    private final OrdersRepository ordersRepository;

    public void addNewClient(Client client) {


        clientRepository.save(client);
    }

    public void addNewOrder(OrderS orderS)
    {
        ordersRepository.save(orderS);
    }

}
