package com.test.spring_test.model;

import com.test.spring_test.enumList.STATUS;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Null;


@Data
@Table
@Entity
public class ProcessResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private STATUS status;

    @OneToOne
    @JoinColumn
    private Money money;
}
