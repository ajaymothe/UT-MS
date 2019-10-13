package ut.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.micrometer.core.ipc.http.HttpSender.Request;
import ut.microservices.repository.DisbursementRepository;
import ut.microservices.model.DisbursementMsTest;

@Service
public class DisbursementService{
	@Autowired
	DisbursementRepository disbursementRepository;
	

	public String disburse(String loan_id) {
		disbursementRepository.setLoanStatusByLoanId(loan_id);
		return "Success";
	}

}