package com.test.spring_test.model;

import com.test.spring_test.dto.ProcessResult;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table
@Data
@Entity
public class CashBack {
    @Id
    private UUID id = c();

    @Column(name = "money")
    private Double money;

    @Column(name = "percent")
    private Long percent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders")
    private OrderS orderS;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result")
    private ProcessResult processResult;

    private UUID c(){
        return UUID.randomUUID();
    }
}
