package com.test.spring_test.dto;

import com.test.spring_test.enumList.STATUS;
import com.test.spring_test.model.Money;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessResultDto implements Serializable {

    private Long id;
    private STATUS status;
    private Money money;

}
