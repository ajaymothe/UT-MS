package ut.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ut.microservices.controller.DisbursementCtrl;

@SpringBootApplication
public class DisbursementApplication {


	public static void main(String[] args) {
		SpringApplication.run(DisbursementApplication.class, args);
	}

}
