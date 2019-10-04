package ut.ms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.json.JSONObject;

@RestController
@RequestMapping("/loan")
public class LoanappCtrl {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    LoanAppService s;

    @CrossOrigin
    @PostMapping("/recieve")
    public String recieveLoan() {
        System.out.println("yes i am here");
        return "loan recieved";
    }

    @CrossOrigin
    @PostMapping(path = "/saveapp", consumes="application/json")
    public String savedata(@RequestBody HashMap<String,String> body) {
        System.out.println(body);
        String values=body.toString();
        System.out.println("values::"+values);
        String val=s.saveData(body);
        if(val=="success"){
            kafkaTemplate.send("loanData",body.toString());
            return "success";    
        }
        kafkaTemplate.send("loanData",body.toString());
        //System.out.println(body.get("apmt_loan_amount"));
    return "ok";
    }

}