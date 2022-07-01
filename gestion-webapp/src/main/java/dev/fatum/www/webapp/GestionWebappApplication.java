package dev.fatum.www.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *  Run maven :
 * ./mvnw install && ./mvnw spring-boot:run -pl gestion-webapp
 */
@SpringBootApplication
@ComponentScan(basePackages = {"dev.fatum.www"})
@EntityScan(basePackages = { "dev.fatum.www.model" })
@EnableJpaRepositories(basePackages = "dev.fatum.www.consumer.dao")
public class GestionWebappApplication extends SpringBootServletInitializer {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:ValidationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GestionWebappApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionWebappApplication.class, args);
	}

}
