package com.example.demo.service.impl;

import com.example.demo.entity.QuoteEntity;
import com.example.demo.model.QuoteModel;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("storeService")
public class StoreService implements IStoreService {

	@Autowired
	private QuoteRepository quoteRepository;

	@Async
	@Override
	public void storeQuote(QuoteModel quoteModel) {
		QuoteEntity qe = new QuoteEntity(quoteModel.getIsin(), quoteModel.getAsk(), quoteModel.getBid());
		quoteRepository.save(qe);
	}

	@Override
	public List<QuoteModel> isinHistory(String isin) {
		return quoteRepository.findAllByIsin(isin)
				.stream()
				.map(it -> new QuoteModel(it.getIsin(), it.getBid(), it.getAsk()))
				.collect(Collectors.toList());
	}
}
