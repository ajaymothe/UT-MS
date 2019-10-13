package ut.ms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "ms_application_test")
//@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Data
public class ApplicationMsTest implements Serializable{
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ap_no")
    private Integer ap_no;
    
    @Column(name = "loan_amount")
    private long loan_amount;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "ap_name")
    private String ap_name;
    
    @Column(name = "ap_email")
    private String ap_email;
    
    @Column(name = "ap_address")
    private String ap_address;
    
    @Column(name = "ap_mobile")
    private String ap_mobile;
    
    
    @Column(name = "ip_address")
    private String ip_address;
    
    @Column(name = "loan_id")
    private String loan_id;
    
    @Column(name = "ap_dob")
    private Date ap_dob;
    
    @Column(name = "ap_gender")
    private String ap_gender;
    
    @Column(name = "loan_tenure")
    private Integer loan_tenure;
    
    @Column(name = "ap_bank_acnt_no")
    private String ap_bank_acnt_no;
    
    @Column(name = "ap_bank_ifsc")
    private String ap_bank_ifsc;
    
    @Override
    public String toString() {
        return "{ap_no="+ap_no+",loan_amount="+loan_amount+",state="+state+",ap_name="+ap_name+",ap_email="+ap_email+
        ",ap_address="+ap_address+",ap_mobile="+ap_mobile+",loan_id="+loan_id+",ap_dob="+ap_dob+",ap_gender="+ap_gender+
        ",loan_tenure="+loan_tenure+",ap_bank_acnt_no="+ap_bank_acnt_no+",ap_bank_ifsc="+ap_bank_ifsc+
        ",ip_address="+ip_address+"}";
    }
}