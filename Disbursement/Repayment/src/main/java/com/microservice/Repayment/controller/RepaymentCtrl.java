package com.microservice.Repayment.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.Basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.Repayment.model.RepaymentMsTest;
import com.microservice.Repayment.repository.RepaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/repayment")
public class RepaymentCtrl {

    @Autowired
    private RepaymentRepository rpayrepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    
    @Basic
    private java.sql.Date date;


    // @Autowired
    // public RepaymentCtrl(RepaymentRepository rpayrepository, KafkaTemplate<String, String> kafkaTemplate){
    //     this.rpayrepository = rpayrepository;
    //     this.kafkaTemplate = kafkaTemplate;
    // } 
    
    @KafkaListener(topics="disbursed",groupId="myGroupId") // topic=disbursed
    public void loanDataFromDisbursementService(@RequestBody String data){ // @RequestBody Map<RepaymentMsTest> data
        System.out.println("data::"+data);
        Map<String,String>map=StringToMap(data);
        RepaymentMsTest rt = new RepaymentMsTest();
        rt.setRpmt_loan_id(map.get("loan_id"));
        rt.setRpmt_loan_tenure(30);
        rt.setRpmt_loan_amount(Double.parseDouble(map.get("loan_amount")));
        rt.setRpmt_repayment_Date(date.valueOf("2019-10-11"));
        rt.setRpmt_cust_name("rahul");
        rt.setRpmt_loan_tenure(30);
        rt.setRpmt_repay_amount(0.00);
        rt.setRpmt_bank_name("CIMB");
        rpayrepository.save(rt);
    }
   
    @CrossOrigin    
    @RequestMapping(value = "/loandetails",consumes="application/json")
    public String getCustomerDataByLoanId(@RequestBody String data){
        // @PathVariable(value="rpmt_loan_id")
        System.out.println("get customer data"+data);
        // Map<String,String> map=StringToMap(data);
        // System.out.println(map);
        //RepaymentMsTest tabledata = rpayrepository.findByLoanId(data);
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = null;
        List<RepaymentMsTest> usersList = (List<RepaymentMsTest>) rpayrepository.findByLoanIdList(data);
        try {
            arrayToJson = objectMapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return arrayToJson;
    }

    @CrossOrigin
    @PostMapping(value = "/repaid")
    public String updateRepaymentStatus(@RequestBody String data) {
        RepaymentMsTest status = rpayrepository.findByLoanId(data);
        status.setRpmt_status("Y");
        rpayrepository.save(status);
        System.out.println("Repayment is done successfully");
        
        kafkaTemplate.send("reconcileData", status.toString());
	return "Repayment is done successfully";
    }
    
    public Map<String,String> StringToMap(String value){
        String body = value.substring(1, value.length()-1);           
        String[] keyValuePairs = body.split(",");            
        Map<String,String> map = new HashMap<>();               
        for(String pair : keyValuePairs){
            String[] entry = pair.split("=");          
            map.put(entry[0].trim(), entry[1].trim());
        }
    return map;
    }

}