package ut.ms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.ms.model.ApplicationMsTest;
import ut.ms.repository.ApplicationMsRepository;

@Service
public class LoanAppService {
    @Autowired
    ApplicationMsRepository repo;

    @Basic
    private java.sql.Date date;

    public String saveData(HashMap<String, String> map){
        ApplicationMsTest tuple=new ApplicationMsTest();
        String loan_id = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        
        tuple.setAp_name(map.get("apmt_full_name"));
        tuple.setAp_email(map.get("apmt_email_id"));
        tuple.setAp_mobile(map.get("apmt_phone_number"));
        tuple.setAp_address(map.get("apmt_address"));
        tuple.setAp_bank_acnt_no(map.get("ap_bank_acnt_no"));
        tuple.setAp_bank_ifsc(map.get("ap_bank_ifsc"));
        tuple.setAp_dob(date.valueOf(map.get("ap_dob")));
        tuple.setAp_gender(map.get("ap_gender"));
        tuple.setLoan_id(loan_id);
        tuple.setLoan_tenure(Integer.valueOf(map.get("loan_tenure")));
        tuple.setLoan_amount(Long.valueOf(map.get("apmt_loan_amount")));
        tuple.setState("N");
        tuple.setIp_address("0.0.0.0");
        repo.save(tuple);
        String data=tuple.toString();
        return "success::"+data;
    }
    
    public String updateData(Map<String, String> map){
        ApplicationMsTest tuple=repo.findByLoanId(map.get("loan_id")).get(0);
        System.out.println("\ndbdata::"+tuple.toString());
        tuple.setAp_name(map.get("ap_name"));
        tuple.setAp_email(map.get("ap_email"));
        tuple.setAp_mobile(map.get("ap_mobile"));
        tuple.setAp_address(map.get("ap_address"));
        tuple.setAp_bank_acnt_no(map.get("ap_bank_acnt_no"));
        tuple.setAp_bank_ifsc(map.get("ap_bank_ifsc"));
        tuple.setAp_dob(date.valueOf(map.get("ap_dob")));
        tuple.setAp_gender(map.get("ap_gender"));
        tuple.setLoan_tenure(Integer.valueOf(map.get("loan_tenure")));
        tuple.setLoan_amount(Long.valueOf(map.get("loan_amount")));
        tuple.setState("F");
        tuple.setIp_address("0.0.0.0");
        repo.save(tuple);
        return "success";
    }
}