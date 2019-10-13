package ut.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


import ut.microservices.model.DisbursementMsTest;

@Component
public interface DisbursementRepository extends JpaRepository<DisbursementMsTest, Long> {

    @Query(value = "SELECT c FROM DisbursementMsTest c where c.dmt_loan_id= :loan_id")
    public List<DisbursementMsTest> findByLoanIdQuery( String loan_id );

    @Query(value = "SELECT c FROM DisbursementMsTest c where c.dmt_status='PENDING'")
    public List<DisbursementMsTest> findByLoanStatus();

    @Modifying
    @Query("update DisbursementMsTest u set u.dmt_status = COMPLETED where u.dmt_loan_id = :loan_id")
    void setLoanStatusByLoanId(String loan_id);

}