package com.test.spring_test.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;


}
