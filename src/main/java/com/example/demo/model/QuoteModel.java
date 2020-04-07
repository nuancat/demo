package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteModel {
	@Pattern(regexp = "^[0-9, A-Z]{12}$", message = "isin is not correct")
	private String isin;
	private Double bid;
	private Double ask;


}
