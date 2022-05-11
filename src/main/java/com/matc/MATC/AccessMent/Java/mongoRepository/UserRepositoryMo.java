package com.matc.MATC.AccessMent.Java.mongoRepository;

import com.matc.MATC.AccessMent.Java.MongoModels.UserMo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMo extends MongoRepository<UserMo, Long> {

    UserMo findByUserName(String userName);

}