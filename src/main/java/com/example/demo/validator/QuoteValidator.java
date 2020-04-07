package com.example.demo.validator;

import com.example.demo.model.QuoteModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteValidator implements Validator {
	Pattern compile = Pattern.compile("^[0-9, A-Z]{12}$");

	@Override
	public boolean supports(Class<?> clazz) {
		return QuoteModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuoteModel quoteModel = (QuoteModel) target;
		Matcher matcher = compile.matcher(quoteModel.getIsin());
		if (!matcher.matches()) {
			errors.rejectValue("ISIN", "error.bad_isin");
		}
		if (quoteModel.getBid() < quoteModel.getAsk()) {
			errors.reject("error.bid_ask_error");
		}
	}
}
