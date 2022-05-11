package com.matc.MATC.AccessMent.Java.service;


import com.matc.MATC.AccessMent.Java.MongoModels.PersonMo;
import com.matc.MATC.AccessMent.Java.exception.PersonNotFoundException;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return personRepository.findById(id).get();
        } else {
            throw new PersonNotFoundException();
        }

    }


    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public String personDelete(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
        return "deleted";
    }

    public Person updatePerson(Long id, Person person) {
        Optional<Person> addResponse = personRepository.findById(id);
        if (addResponse.isPresent() && addResponse.get().getPersonId().equals(id)) {
            person.setPersonId(id);
            return personRepository.save(person);

        } else {
            throw new PersonNotFoundException();
        }

    }

    public List<Person> getPersonPagination(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Person> pagedResult = personRepository.findAll(pageRequest);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Person>();
        }
    }
}
