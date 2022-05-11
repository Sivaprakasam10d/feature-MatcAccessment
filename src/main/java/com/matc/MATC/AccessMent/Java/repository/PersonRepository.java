package com.matc.MATC.AccessMent.Java.repository;

import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findById(Long aLong);

    Person findByEmail(String email);

   Person findByMobileNumber(String mobileNumber);
}