package ut.microervices.resourse;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/mail")
public class LoanController {
	//private KafkaTemplate<String,String> kafkaTemplate;
	@Autowired
	Gson jsonConvertor;
	private JavaMailSender javaMailSender;
	private KafkaTemplate<String, String> kafkaTemplate;
	    
	public LoanController(KafkaTemplate<String, String> kafkaTemplate, Gson jsonConvertor) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	@CrossOrigin
	@PostMapping("/send")
	@KafkaListener(topics="loanReceived",groupId="myGroupId")
	public void loanReceivedConsumer(String body){//@RequestBody String emailId
		//System.out.print("data::"+body);
		System.out.println("done with mail::"+body+" is in notification");
		recieveLoanApplication(body);
	}
	
	public String recieveLoanApplication(String body) {//@RequestBody String body
		System.out.print("Producer received data : "+body);
		kafkaTemplate.send("mailsuccess","ajay.mothe@uangteman.com");
		return "Loan Application Recieved";
	}

}
