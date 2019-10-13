package ut.microservices.DvStaffManager.repository;

import java.util.List;

import ut.microservices.DvStaffManager.models.ApplicationDataMsTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDataMsTestRepository extends CrudRepository<ApplicationDataMsTest, Long> {



    @Query(value = "SELECT c FROM ApplicationDataMsTest c where c.apmt_email_id= :email_id")
    public List<ApplicationDataMsTest> findByEmailQuery(String email_id );


}