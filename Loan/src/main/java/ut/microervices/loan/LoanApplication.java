package ut.microervices.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ut.microervices.resourse.LoanController;

@SpringBootApplication
@ComponentScan(basePackageClasses=LoanController.class)
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
