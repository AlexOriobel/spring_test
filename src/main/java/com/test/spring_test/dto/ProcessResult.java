package com.test.spring_test.dto;

import com.test.spring_test.Status;
import com.test.spring_test.model.CashBack;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@Table
@Entity
public class ProcessResult {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "status")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "result", referencedColumnName = "money"),
            @JoinColumn(name = "percent", referencedColumnName = "percent")
    })
    private CashBack cashBack;

}
