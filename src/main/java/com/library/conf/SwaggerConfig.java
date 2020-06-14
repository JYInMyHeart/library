package com.library.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
@Component("swagger2Config")
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
		ParameterBuilder tokenParameter = new ParameterBuilder();
		tokenParameter.name("token").description("身份验证token，登录时获取。")
				.modelRef(new ModelRef("string")).parameterType("header")
				//.modelRef(new ModelRef("string")).parameterType("cluster_id")
				.required(false).build();

		List<Parameter> parameters = new ArrayList<>();
		parameters.add(tokenParameter.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.library.controller"))
				//.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.globalOperationParameters(parameters)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("图书管理系统").description("图书管理接口信息")
				.version("1.0").build();
		return apiInfo;
	}
}