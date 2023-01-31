package uz.md.bookservicewithwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
@EnableFeignClients
public class BookServiceWithWebfluxApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookServiceWithWebfluxApplication.class, args);
	}

}
