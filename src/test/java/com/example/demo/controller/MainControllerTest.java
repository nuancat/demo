package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@WebAppConfiguration
class MainControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	void enLevel_badRequest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/en-level")
				.param("isin", "dont_pass"))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@Test
	void enLevel_emptyEnlevel() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/en-level")
				.param("isin", "RU000A0JX0J2"))
				.andExpect(status().isOk())
				.andExpect(content().json("0"))
				.andDo(print());
	}

}