package com.test.spring_test.dto;

import com.test.spring_test.model.Client;
import com.test.spring_test.model.OrderS;
import com.test.spring_test.model.ProcessResult;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class CashBackDto implements Serializable {

    public String id;
    public Client client;
    public OrderS orderS;
    public ProcessResult processResult;

}
