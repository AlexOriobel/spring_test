package com.test.spring_test.model;

import com.test.spring_test.enumList.STATUS;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Data
@Table
@Entity
public class Money {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Double money;

	@Column
	private Double percent;

}

