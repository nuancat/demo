package com.example.demo.repository;

import com.example.demo.entity.QuoteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<QuoteEntity, Long> {
	List<QuoteEntity> findAllByIsin(String isin);
}
