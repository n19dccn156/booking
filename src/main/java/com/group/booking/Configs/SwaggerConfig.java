package com.group.booking.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    
    @Bean
	public Docket postsApi() {
		return 
			new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
					.useDefaultResponseMessages(false)
						.apiInfo(apiInfo())
							.select()
								.paths(regex("/api/.*"))
									.build();
	}
	
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Booking Hotel API")
			.description("Hotel booking API reference for developers")
				.version("1.0")
					.build();
	}
}
