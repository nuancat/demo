package com.example.demo.controller;

import com.example.demo.model.QuoteModel;
import com.example.demo.service.IMainService;
import com.example.demo.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Validated
@RestController()
public class MainController {

	@Autowired
	@Qualifier("mainService")
	IMainService mainService;

	@Autowired
	@Qualifier("storeService")
	IStoreService storeService;

	@PostMapping("/post-quote")
	public ResponseEntity<Object> post(@Valid @RequestBody QuoteModel quoteModel) {
		if (quoteModel.getBid() == null && quoteModel.getAsk() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (quoteModel.getBid() != null && quoteModel.getAsk() != null && quoteModel.getBid() > quoteModel.getAsk()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		storeService.storeQuote(quoteModel);
		mainService.processEnLv(quoteModel);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/en-level")
	public Double get(@RequestParam(value = "isin") @Pattern(regexp = "^[0-9, A-Z]{12}$", message = "Invalid ISIN") String isin) {
		return mainService.levelByIsin(isin);
	}

	@GetMapping("/available-isin")
	public ConcurrentHashMap.KeySetView<String, Double> availableIsin() {
		return mainService.availableIsin();
	}

	@GetMapping("/history-isin")
	public List<QuoteModel> historyIsin(@RequestParam String isin) {
		return storeService.isinHistory(isin);
	}

}
