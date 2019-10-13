package com.microservice.Repayment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "repayment_ms_test")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Data
public class RepaymentMsTest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long rpmt_id;

    @NotBlank
    @Column(name="rpmt_loan_id")  
    //@JsonProperty(value = "rpmt_loan_id", required = true)  
    private String rpmt_loan_id;
    
    @Column(name="rpmt_cust_name")
    //@JsonProperty(value = "rpmt_cust_name", required = true)
    private String rpmt_cust_name;

    @Column(name="rpmt_loan_amount") 
    //@JsonProperty(value = "rpmt_loan_amount", required = true)       
    private Double rpmt_loan_amount;

    @Column(name="rpmt_loan_tenure")
    //@JsonProperty(value = "rpmt_loan_tenure", required = true)      
    private Integer rpmt_loan_tenure;
    
    @Column(name="rpmt_repay_amount")   
    //@JsonProperty(value = "rpmt_repay_amount", required = true)         
    private Double rpmt_repay_amount;

    @Column(name="rpmt_repayment_Date") 
    //@JsonProperty(value = "rpmt_repayment_Date", required = true)   
    private Date rpmt_repayment_Date;

    @Column(name="rpmt_bank_name")  
    //@JsonProperty(value = "rpmt_bank_name", required = true)     
    private String rpmt_bank_name;

    @Column(name="rpmt_status")     
    //@JsonProperty(value = "rpmt_status", required = true)   
    private String rpmt_status;
 
    @Column(name = "email_id")
    private String email_id;

    @Column(name="rpmt_created_at",nullable = false)
    @CreationTimestamp
    private Date rpmt_created_at;

    // @CreatedDate
    // private Date rpmt_created_at;

    @Column(name="rpmt_updated_at",nullable = false)
    @CreationTimestamp
    private Date rpmt_updated_at;

    //@Temporal(TemporalType.TIMESTAMP)
    
    // @LastModifiedDate
    // private Date rpmt_updated_at;

    @Override
    public String toString() {
        return "{rpmt_bank_name=" + rpmt_bank_name + ", rpmt_created_at=" + rpmt_created_at
                + ", rpmt_cust_name=" + rpmt_cust_name + ", rpmt_id=" + rpmt_id + ", rpmt_loan_amount="
                + rpmt_loan_amount + ", rpmt_loan_id=" + rpmt_loan_id + ", rpmt_loan_tenure=" + rpmt_loan_tenure
                + ", rpmt_repay_amount=" + rpmt_repay_amount + ", rpmt_repayment_Date=" + rpmt_repayment_Date + ",email_id="+email_id+"}";
    }


}