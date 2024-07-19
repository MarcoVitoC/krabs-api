package portfolio.krabs.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrabsApiApplication {

	public static void main(String[] args) {
		System.out.println("Hello World, This is Krabs App!");
		SpringApplication.run(KrabsApiApplication.class, args);
	}

}
