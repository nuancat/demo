package com.example.demo.service;

import com.example.demo.model.QuoteModel;

import java.util.List;

public interface IStoreService {

	void storeQuote(QuoteModel quoteModel);

	List<QuoteModel> isinHistory(String isin);
}
