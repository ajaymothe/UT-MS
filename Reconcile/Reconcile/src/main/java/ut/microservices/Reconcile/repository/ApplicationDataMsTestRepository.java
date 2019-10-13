package ut.microservices.Reconcile.repository;

import java.util.List;

import ut.microservices.Reconcile.models.ApplicationDataMsTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDataMsTestRepository extends CrudRepository<ApplicationDataMsTest, Long> {



    @Query(value = "SELECT c FROM ApplicationDataMsTest c where c.rpmt_id= :rpmt_id")
    public ApplicationDataMsTest findByapmtId(Long rpmt_id );

    @Query(value = "SELECT c FROM ApplicationDataMsTest c where c.rpmt_status= :rpmt_status")
    public List<ApplicationDataMsTest> findBystatus(String rpmt_status );


}