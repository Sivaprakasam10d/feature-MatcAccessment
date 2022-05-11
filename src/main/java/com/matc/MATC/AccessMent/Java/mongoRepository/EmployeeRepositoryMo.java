package com.matc.MATC.AccessMent.Java.mongoRepository;

import com.matc.MATC.AccessMent.Java.MongoModels.EmployeeMo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepositoryMo extends MongoRepository<EmployeeMo, Long> {

    EmployeeMo findByEmpId(Long empId);
}

