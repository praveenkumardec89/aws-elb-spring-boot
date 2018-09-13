package nl.moneyyou;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.moneyyou")
@EnableCircuitBreaker
public class Moneyyou extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Moneyyou.class);
    }

	public static void main(String... args) {
		new SpringApplicationBuilder(Moneyyou.class).run(args);
    }

}
