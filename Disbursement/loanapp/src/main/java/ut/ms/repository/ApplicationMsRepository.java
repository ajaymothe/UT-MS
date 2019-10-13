package ut.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


import ut.ms.model.ApplicationMsTest;

@Component
public interface ApplicationMsRepository extends JpaRepository<ApplicationMsTest,Long>{

    @Query(value = "SELECT c FROM ApplicationMsTest c where c.ap_email=:ap_email")
    public List<ApplicationMsTest> findByEmail(String ap_email);

    @Query(value = "SELECT c FROM ApplicationMsTest c where c.loan_id=:loan_id")
    public List<ApplicationMsTest> findByLoanId(String loan_id);

}