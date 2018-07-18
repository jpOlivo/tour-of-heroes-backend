package tourheroes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	 
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("tourheroes.controllers"))
				.paths(PathSelectors.regex("/api.*")).build().apiInfo(getApiInfo())
				.useDefaultResponseMessages(false);
	}
	
	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				//.validatorUrl(null) FIXME: don't work, see https://github.com/springfox/springfox/issues/2201
				.validatorUrl("")				
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Tour-of-Heroes REST API")
				.description("An REST API for performing CRUD operations over Hero documents on a MongoDB instance.")
				.version("1.0")
				.termsOfServiceUrl("Terms of service")
				.contact(new Contact("Juan Olivera", "https://jpolivo.github.io/resume/", "juam17@gmail.com"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
	
}