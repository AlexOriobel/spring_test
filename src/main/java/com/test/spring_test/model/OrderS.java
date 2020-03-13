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
@AllArgsConstructor
@NoArgsConstructor
public class OrderS implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String data;

	@Column
	private String ord;

	@Column
	private Double cost;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Client client;

}
