package com.test.spring_test.dto;

import com.test.spring_test.Status;
import com.test.spring_test.model.CashBack;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Data
@Table
@Entity
public class ProcessResult {


    @Id
    private UUID id = c();

    @Column(name = "status")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "result", referencedColumnName = "money"),
            @JoinColumn(name = "percent", referencedColumnName = "percent")
    })
    private CashBack cashBack;

    private UUID c(){
        UUID uuid= UUID.randomUUID();
        return uuid;
    }
}
