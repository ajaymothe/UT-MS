package com.microservice.Repayment.repository;

import java.util.List;

import com.microservice.Repayment.model.RepaymentMsTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RepaymentRepository extends JpaRepository<RepaymentMsTest, Long>{

    @Query(value = "SELECT c FROM RepaymentMsTest c where c.rpmt_loan_id = :loan_id")
    public List<RepaymentMsTest> findByLoanIdList(String loan_id);

    @Query(value = "SELECT c FROM RepaymentMsTest c where c.rpmt_loan_id = :loan_id")
    public RepaymentMsTest findByLoanId(String loan_id);
}