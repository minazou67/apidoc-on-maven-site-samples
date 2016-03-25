package org.minazou67.samples.apidoc;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("org.minazou67.samples.apidoc.controller")
public class SwaggerConfig {

	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				.ignoredParameterTypes(Model.class, Locale.class, SessionStatus.class);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Sample Application API")
				.description("This is a REST API for sample applications.")
				.version(ApiInfo.DEFAULT.getVersion())
				.build();
	}
}
