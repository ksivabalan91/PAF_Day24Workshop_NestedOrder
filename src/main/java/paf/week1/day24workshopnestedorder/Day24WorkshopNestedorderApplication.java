package paf.week1.day24workshopnestedorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Day24WorkshopNestedorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day24WorkshopNestedorderApplication.class, args);
	}

	@Bean
	public CommonsRequestLoggingFilter log(){
	CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
	logger.setIncludeClientInfo(true);
	logger.setIncludeQueryString(true);
	return logger;
}

}
