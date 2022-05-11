package com.matc.MATC.AccessMent.Java.mongoRepository;

import com.matc.MATC.AccessMent.Java.MongoModels.PersonMo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositoryMo extends MongoRepository<PersonMo, Long> {
    
    PersonMo findByMobileNumber(String mobileNumber);
}