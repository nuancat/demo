package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@SpringBootApplication
public class DemoApplication implements AsyncConfigurer {

	public static final int OPTIMAL_THREADS_FOR_EXECUTOR = 25;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		return Executors.newFixedThreadPool(OPTIMAL_THREADS_FOR_EXECUTOR);
	}
}
