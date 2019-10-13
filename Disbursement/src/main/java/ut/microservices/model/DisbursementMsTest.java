package ut.microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "disbursement_ms_test")
//@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Data
public class DisbursementMsTest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dmt_id")
    private Long dmt_id;

    @NotBlank
    @Column(name="dmt_loan_id")
    private String dmt_loan_id;

    
    @Column(name="dmt_loan_amount")
    private Long dmt_loan_amount;

    @NotBlank
    @Column(name="dmt_bank_id")
    private String dmt_bank_id;

    @NotBlank
    @Column(name="dmt_email_id")
    private String dmt_email_id;

    @NotBlank
    @Column(name="dmt_status")
    private String dmt_status;

    @Column(name="dmt_created_at",nullable = false)
    @CreationTimestamp
    private Date dmt_created_at;

    @Override
    public String toString() {
        return "DisbursementMsTest [dmt_loan_id ="+dmt_loan_id+",dmt_loan_amount="+dmt_loan_amount+",disbursed_at="+dmt_created_at+"] ";
    }

}