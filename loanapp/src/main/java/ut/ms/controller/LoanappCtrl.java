package ut.ms.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
    public String savedata(@RequestBody HashMap<String,String> body) throws ParseException {
        String val=s.saveData(body);
        String[] values = val.split("::");
        if(values[0].equals("success")){
            kafkaTemplate.send("loanData",values[1]);
            return "success";    
        }else{
            return "Error";
        }
    }

    @KafkaListener(topics = "updateLoanData",groupId = "myGroupId")
    public void updateData(String value){
        System.out.println("\nupdateString::"+value);
        Map<String,String> map = StringToMap(value.toString());
        System.out.println("\nupdatefun::"+map);
        String response=s.updateData(map);
        System.out.println(response);
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