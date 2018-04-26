package org.aexp.springbootcouchbase.mvc.repository;

import org.aexp.springbootcouchbase.mvc.model.Student;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "student", viewName = "all")
@Repository
public interface UserRepository extends CouchbaseRepository<Student, String> {

    List<Student> findByName(String name);

    @Query("#{#n1ql.selectEntity}  WHERE name = 'xxx' AND #{#n1ql.filter}")
    List<Student> findByTheQuery(@Param("name") String name);

    @Query(value = "Select META(sampleData).id as _ID, META(sampleData).cas as _CAS, sampleData.* from sampleData")
    List<Student> getAllOrderAndGroup();
}
