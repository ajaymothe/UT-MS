package ut.microservices.Reconcile.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ut.microservices.Reconcile.models.ApplicationDataMsTest;
import ut.microservices.Reconcile.models.Greeting;
import ut.microservices.Reconcile.repository.ApplicationDataMsTestRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reconcile")
public class DvStaffManagerController {
    @Autowired
    ApplicationDataMsTestRepository appdataRepository;
    
    @Autowired
    private SimpMessagingTemplate msgTemplate;
    
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "RepaymentDate",groupId = "myGroupId")
    public void storeLoanData(@RequestBody String value) {
        ApplicationDataMsTest user = new ApplicationDataMsTest();
        Map<String,String> name = StringToMap(value.toString());
        System.out.println("kafka recieved");
        user.setRpmt_cust_name("rahul");
        user.setRpmt_status("N");
        user.setEmail_id(name.get("email_id"));
        user.setRpmt_bank_name("CIMB");
        //System.out.println(name.get("loan_amount"));
        user.setRpmt_loan_amount(Long.valueOf(1000));
        user.setRpmt_repay_amount(Long.valueOf(1000));
        Date date = new Date();
        user.setRpmt_updated_at(date);
        user.setRpmt_created_at(date);
        // System.out.println(user);
        // System.out.println(user.getApmt_full_name());
        appdataRepository.save(user);
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

    @CrossOrigin
    @GetMapping(path = "/notes")
    public String getAllNotes() {

        // List<ApplicationDataMsTest> models = appdatatestRepository.findAll();
        // for(ApplicationDataMsTest model : models) {
        // System.out.println(model.getApmt_full_name());
        // }
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayToJson = null;
        List<ApplicationDataMsTest> usersList = (List<ApplicationDataMsTest>) appdataRepository.findBystatus("N");

        try {
            arrayToJson = objectMapper.writeValueAsString(usersList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(arrayToJson);
        return arrayToJson;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(ApplicationDataMsTest message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println(message);
        return new Greeting(message.toString());
    }

    @MessageMapping("/accepted")
    @SendTo("/topic/greetings")
    public Greeting accepted(ApplicationDataMsTest message) throws Exception {
        Thread.sleep(1000);
        System.out.println(message.getRpmt_id());
        ApplicationDataMsTest userupdate = appdataRepository.findByapmtId(message.getRpmt_id());
        System.out.println(userupdate);
        userupdate.setRpmt_status("Y");
        appdataRepository.save(userupdate);
        return new Greeting(message.getRpmt_id().toString());
    }

    @RequestMapping(value = "/send-data", method = RequestMethod.GET)
    @SendTo("/topic/greetings")
    public String sendData() {

        // User user = new User();
        // user.id = UUID.fromString(id);
        // user.stringData = data;
        // database.saveStringData(user);
        msgTemplate.convertAndSend("/topic/greetings", "Hello, abc");
        return "successful";
    }

    @CrossOrigin
    @PostMapping("/update-cust-info")
    public String UpdateData(@RequestBody HashMap<String, String> name) {

        Long ap_id = Long.parseLong(name.get("apmt_id"));
        Optional<ApplicationDataMsTest> userupdate = appdataRepository.findById(ap_id);
        System.out.println(userupdate);
        // userupdate.se

    return "Changes Made Succesfully";
}
    

    // @CrossOrigin
    // @PostMapping("/get-cust-info")
    // public String getbyName(@RequestBody HashMap<String, String> name) {//String name) {
    //     // List<ApplicationDataMsTest> models = appdatatestRepository.findAll();
    //     // for(ApplicationDataMsTest model : models) {
    //     // System.out.println(model.getApmt_full_name());
    //     // }
    //     // name.forEach((x, y) -> {
    //     // appdataRepository.save(name.get(x));
    //     // });
        
    //     //mailSuccessfullySent("hello");
    //     ApplicationDataMsTest user = new ApplicationDataMsTest();
    //      System.out.println(user);
    //     // // System.out.println(name.get("apmt_full_name"));
    //     // try{
    //     user.setApmt_full_name(name.get("apmt_full_name"));
    //     user.setApmt_apli_status("N");
    //     user.setApmt_address(name.get("apmt_address"));
    //     user.setApmt_email_id(name.get("apmt_email_id"));
    //     user.setApmt_ip_address("0.0.0.0");
    //     System.out.println(name.get("apmt_loan_amount"));
    //     user.setApmt_loan_amount(Long.valueOf(1000));
    //     user.setApmt_phone_number(name.get("apmt_phone_number"));
    //     user.setApmt_reject_reason("null");
    //     Date date = new Date();
    //     user.setApmt_updated_at(date);
    //     user.setApmt_created_at(date);

    //     // System.out.println("Hii");
    //     System.out.println(user);
    //     System.out.println(user.getApmt_full_name());
    //     // // user.setApmt_id(Long.valueOf(5));
    //     // // Date dateobj = new Date();
    //     // // user.setApmt_created_at(dateobj);
    //     // // user.setApmt_updated_at(dateobj);
    //     // System.out.println(user);
    //     appdataRepository.save(user);
    //     // }
    //     // catch(DataAccessException d){
    //     //     d.printStackTrace();
    //     // }
    //     // catch(PersistenceException p){
    //     //     p.printStackTrace();
    //     // }
    //     // catch(Exception e){
    //     //     e.printStackTrace();
    //     // }
    //     //kafkaTemplate.send("loanReceived",name.get("apmt_email_id"));

    //     return "Application Successful";
    // }

    

}