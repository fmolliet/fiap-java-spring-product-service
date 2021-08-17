package br.com.tomatch.products.config.swagger;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfigurations {
	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.tomatch.products")).paths(PathSelectors.any())
				.build()
				// .ignoredParameterTypes(Usuario.class)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder().name("Authorization").description("Header para token gerar o token JWT")
								// Tipo do parametro
								.modelRef(new ModelRef("string"))
								// Onde vai o parametro
								.parameterType("header").required(false).build()));
	}
}
