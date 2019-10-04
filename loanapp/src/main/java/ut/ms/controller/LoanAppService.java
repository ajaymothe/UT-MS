package ut.ms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.micrometer.core.ipc.http.HttpSender.Request;
import ut.ms.model.ApplicationMsTest;
import ut.ms.repository.ApplicationMsRepository;

@Service
public class LoanAppService {
    @Autowired
    ApplicationMsRepository repo;

    public String saveData(HashMap<String,String> map){
        ApplicationMsTest tuple=new ApplicationMsTest();
        //apmt_loan_amount=65556, apmt_address=hgghjg, apmt_phone_number=765675, apmt_full_name=ghghgh, apmt_email_id=ghghjg@hsmms.com
        System.out.println(map.get("apmt_loan_amount"));
        tuple.setAp_name(map.get("apmt_full_name"));
        tuple.setAp_email(map.get("apmt_email_id"));
        tuple.setAp_mobile(map.get("apmt_phone_number"));
        tuple.setAp_address(map.get("apmt_address"));
        tuple.setLoan_amount(Long.valueOf(map.get("apmt_loan_amount")));
        tuple.setState("N");
        tuple.setIp_address("0.0.0.0");
        repo.save(tuple);
        return "success";
    }
}