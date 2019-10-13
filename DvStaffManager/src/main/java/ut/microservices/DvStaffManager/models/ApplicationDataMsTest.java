package ut.microservices.DvStaffManager.models;

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
@Table(name = "dvstaffman_data_ms_test")
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
    @Column(name="apmt_id")
    private Long apmt_id;

    @NotBlank
    @Column(name="apmt_ip_address")
    private String apmt_ip_address;

    @NotBlank
    @Column(name="apmt_full_name")
    private String apmt_full_name;

    @NotBlank
    @Column(name="apmt_email_id")
    private String apmt_email_id;

    @NotBlank
    @Column(name="apmt_phone_number")
    private String apmt_phone_number;

    @NotBlank
    @Column(name="apmt_address")
    private String apmt_address;

    
    @Column(name="apmt_loan_amount")
    private Long apmt_loan_amount;

    @NotBlank
    @Column(name="apmt_apli_status")
    private String apmt_apli_status;

    @NotBlank
    @Column(name="apmt_reject_reason")
    private String apmt_reject_reason;

    @Column(name="apmt_created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date apmt_created_at;

    @Column(name="apmt_updated_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date apmt_updated_at;

    @Override
    public String toString() {
        return "{'apmt_address':" + '"'+ apmt_address+'"' + ", 'apmt_apli_status':" + '"'+apmt_apli_status+'"'
                + ", 'apmt_created_at':" + '"'+apmt_created_at+'"' + ", 'apmt_email_id':" + '"'+apmt_email_id+'"' + ", 'apmt_full_name':"
                + '"'+apmt_full_name+'"' + ", 'apmt_id':" + '"'+apmt_id+'"' + ", 'apmt_ip_address':" + '"'+apmt_ip_address+'"'
                + ", 'apmt_loan_amount':" + '"'+apmt_loan_amount+'"' + ", 'apmt_phone_number':" + '"'+apmt_phone_number+'"'
                + ", 'apmt_reject_reason':" + '"'+apmt_reject_reason+'"' + ", 'apmt_updated_at':" + '"'+apmt_updated_at+'"' + "}";
    }

	// public String getApmt_full_name() {
	// 	return apmt_full_name;
	// }

	// public void setApmt_full_name(String apmt_full_name) {
	// 	this.apmt_full_name = apmt_full_name;
	// }

    // Getters and Setters ... (Omitted for brevity)
}