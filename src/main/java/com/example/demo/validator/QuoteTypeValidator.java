package com.example.demo.validator;

import com.example.demo.interfaces.QuoteValid;
import com.example.demo.model.QuoteModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteTypeValidator implements ConstraintValidator<QuoteValid, QuoteModel> {
	Pattern compile = Pattern.compile("^[0-9, A-Z]{12}$");

	@Override
	public boolean isValid(QuoteModel quoteModel, ConstraintValidatorContext context) {
			Matcher matcher = compile.matcher(quoteModel.getIsin());
			return quoteModel.getBid() < quoteModel.getAsk() && matcher.matches();
	}
}
