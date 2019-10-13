package ut.microservices.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.repository.DisbursementRepository;
import ut.microservices.model.DisbursementMsTest;
import ut.microservices.service.DisbursementService;

@RestController
@RequestMapping("/ut")
public class DisbursementCtrl {

  @Autowired
  DisbursementRepository disbursementRepository;

  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  DisbursementService s2;

  @CrossOrigin
  @PostMapping("/disbursed")
  public String disburse(@RequestBody String loan_id) {
    DisbursementMsTest user = disbursementRepository.findByLoanIdQuery(loan_id).get(0);
    user.setDmt_status("COMPLETED");
    disbursementRepository.save(user);
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("loan_id", loan_id);
    map.put("loan_amount", user.getDmt_loan_amount().toString());
    map.put("email_id", user.getDmt_email_id());
    //map.put("loan_amount", "4500");
    //System.out.println("successs");
    kafkaTemplate.send("disbursed", map.toString());
    return "Done";
  }

  @KafkaListener(topics = "approved",groupId = "myGroupId")
  public void kafkaListenerExample(String param) {
    param = param.replaceAll("[\\(\\)\\[\\]\\{\\}]", "");
    Map<String, String> map = Arrays.stream(param.split(",")).map(entry -> entry.split("="))
        .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
    System.out.println(param);
    String loan_amount = null;
    String loan_id = null;
    for (Entry<String, String> entry : map.entrySet()) {
      if (entry.getKey().equalsIgnoreCase("loan_amount")) {
        loan_amount = entry.getValue().toString();
      } else {
        loan_id = entry.getValue().toString();
      }
    }
    saverecord(loan_id, loan_amount);
  }

  private void saverecord(String loan_id, String loan_amount) {
    DisbursementMsTest user = new DisbursementMsTest();
    user.setDmt_bank_id("001");
    user.setDmt_email_id("user001@gmail.com");
    user.setDmt_loan_amount(Long.valueOf(loan_amount));
    user.setDmt_loan_id(loan_id);
    user.setDmt_status("PENDING");
    disbursementRepository.save(user);
    System.out.println("Record Created");
  }

  @CrossOrigin
  @GetMapping(path = "/get-cust-info")
  public @ResponseBody String getbyName() {
    ObjectMapper objectMapper = new ObjectMapper();
    String arrayToJson = null;
    List<DisbursementMsTest> usersList = disbursementRepository.findByLoanStatus();
    try {
      arrayToJson = objectMapper.writeValueAsString(usersList);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
        return arrayToJson;
    }

@CrossOrigin
@GetMapping(path = "/example")
public @ResponseBody String getExample() {
   return "hello";
}
}
