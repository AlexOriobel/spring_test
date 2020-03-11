package com.test.spring_test.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table
@Data
@Entity
public class CashBack implements Serializable {
	@Id
	@GeneratedValue(generator = "document-uuid2")
	@GenericGenerator(name = "document-uuid2", strategy = "uuid2")
	private String id;

	@Column
	private Double money;

	@Column
	private Long percent;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Client client;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private OrderS orderS;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private ProcessResult processResult;


}
