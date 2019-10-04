package ut.ms.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.NumberUtils;




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
    
    @Override
    public String toString() {
        return "ApplicationMsTest[ap_no="+ap_no+",loan_amount="+loan_amount+",state="+state+",ap_name="+ap_name+
        ",ap_email="+ap_email+",ap_address="+ap_address+",ap_mobile="+ap_mobile+",ip_address="+ip_address+"]";
    }
}