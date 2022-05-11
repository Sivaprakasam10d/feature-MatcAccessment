package com.matc.MATC.AccessMent.Java.mongoRepository;

import com.matc.MATC.AccessMent.Java.MongoModels.AddressMo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryMo extends MongoRepository<AddressMo, Long> {
//    @Query("SELECT new com.matc.MATC.AccessMent.Java.model.Address(a.addId , e.empId) FROM Address a JOIN c.employee e")
//    List<Address> findByEmployeeId(Long empId);
}


