package org.minazou67.samples.apidoc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:META-INF/spring/application-config.xml" })
public class Swagger2MarkupTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void convertSwaggerToAsciiDoc() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(Swagger2MarkupResultHandler.outputDirectory("src/site/asciidoc").build())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}