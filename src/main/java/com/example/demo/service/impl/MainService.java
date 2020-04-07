package com.example.demo.service.impl;

import com.example.demo.model.QuoteModel;
import com.example.demo.service.IMainService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service("mainService")
public class MainService implements IMainService {

	private ConcurrentHashMap<String, Double> energyLevelByIsin = new ConcurrentHashMap<>();

	@Override
	public void processEnLv(QuoteModel quoteModel) {
		String isin = quoteModel.getIsin();
		synchronized (MainService.class) {
			Double energyLevel = energyLevelByIsin.get(isin);
			if (energyLevel == null) { // if isin doesnt exists
				if (quoteModel.getBid() != null) {
					energyLevel = quoteModel.getBid();
				} else {
					energyLevel = quoteModel.getAsk();
				}
			} else { // if isin exists
				if (quoteModel.getBid() > energyLevel) {
					energyLevel = quoteModel.getBid();
				} else if (quoteModel.getAsk() <energyLevel){
					energyLevel = quoteModel.getAsk();
				}
			}
			energyLevelByIsin.put(isin, energyLevel);
		}
	}

	@Override
	public ConcurrentHashMap.KeySetView<String, Double> availableIsin() {
		return energyLevelByIsin.keySet();
	}

	@Override
	public Double levelByIsin(String isin)	 {
		return energyLevelByIsin.getOrDefault(isin, 0.0);
	}
}
