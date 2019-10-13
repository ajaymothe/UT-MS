package ut.microservices.Reconcile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "recon_ms_test")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
@Data
public class ApplicationDataMsTest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rpmt_id")
    private Long rpmt_id;

    @NotBlank
    @Column(name="rpmt_cust_name")
    private String rpmt_cust_name;

    @NotBlank
    @Column(name="email_id")
    private String email_id;

    
    @Column(name="rpmt_loan_amount")
    private Long rpmt_loan_amount;

    @Column(name="rpmt_repay_amount")
    private Long rpmt_repay_amount;

    @NotBlank
    @Column(name="rpmt_status")
    private String rpmt_status;

    @NotBlank
    @Column(name="rpmt_bank_name")
    private String rpmt_bank_name;

    @Column(name="rpmt_created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date rpmt_created_at;

    @Column(name="rpmt_updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date rpmt_updated_at;

    @Override
    public String toString() {
        return "{rpmt_bank_name=" + rpmt_bank_name + ", rpmt_created_at=" + rpmt_created_at
                + ", rpmt_cust_name=" + rpmt_cust_name + ", rpmt_id=" + rpmt_id + ", rpmt_loan_amount="
                + rpmt_loan_amount + ", rpmt_repay_amount=" + rpmt_repay_amount + ",email_id="+email_id+"}";
    }

	// public String getApmt_full_name() {
	// 	return apmt_full_name;
	// }

	// public void setApmt_full_name(String apmt_full_name) {
	// 	this.apmt_full_name = apmt_full_name;
	// }

    // Getters and Setters ... (Omitted for brevity)
}