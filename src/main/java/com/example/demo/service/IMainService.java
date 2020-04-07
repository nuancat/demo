package com.example.demo.service;

import com.example.demo.model.QuoteModel;

import java.util.concurrent.ConcurrentHashMap;

public interface IMainService {
	void processEnLv(QuoteModel quoteModel);

	ConcurrentHashMap.KeySetView<String, Double> availableIsin();

	Double levelByIsin(String isin);
}
