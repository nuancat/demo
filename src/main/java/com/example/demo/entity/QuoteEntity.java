package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class QuoteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String isin;
	private Double ask;
	private Double bid;

	public QuoteEntity() {
	}

	public QuoteEntity(String isin, Double ask, Double bid) {
		this.isin = isin;
		this.ask = ask;
		this.bid = bid;
	}
}
