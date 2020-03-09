package com.test.spring_test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class OrderS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "orderName", nullable = false)
    private String orders;

    @Column(name = "cost", nullable = false)
    private Double cost;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "client_id")
    private Client clientOfOrders;

}
