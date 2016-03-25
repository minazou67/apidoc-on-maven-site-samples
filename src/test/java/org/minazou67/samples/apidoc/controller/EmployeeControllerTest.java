package org.minazou67.samples.apidoc.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.minazou67.samples.apidoc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/spring/application-config.xml" })
public class EmployeeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void addEmployee() throws Exception {
		Employee employee = new Employee(1, "Foo");
		String json = new ObjectMapper().writeValueAsString(employee);

		mockMvc.perform(post("/employee")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void getEmployee() throws Exception {
		mockMvc.perform(get("/employee/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Foo")));
	}

	@Test
	public void updateEmployee() throws Exception {
		Employee employee = new Employee(3, "Foo");
		String json = new ObjectMapper().writeValueAsString(employee);

		mockMvc.perform(put("/employee/3")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(3)));
	}

	@Test
	public void deleteEmployee() throws Exception {
		mockMvc.perform(delete("/employee/4"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(4)));
	}
}
