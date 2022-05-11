package com.matc.MATC.AccessMent.Java.mongoServices;


import com.matc.MATC.AccessMent.Java.MongoModels.PersonMo;
import com.matc.MATC.AccessMent.Java.exception.PersonNotFoundException;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.mongoRepository.PersonRepositoryMo;
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
public class PersonServiceMongo {

    @Autowired
    PersonRepositoryMo personRepositoryMo;


    public PersonMo getPersonMoById(Long id) {
        Optional<PersonMo> person = personRepositoryMo.findById(id);
        if (person.isPresent()) {
            return personRepositoryMo.findById(id).get();
        } else {
            throw new PersonNotFoundException();
        }

    }

    public List<PersonMo> getAllPersonMo() {
        return personRepositoryMo.findAll();
    }

    public PersonMo save(PersonMo personMo) {
        return personRepositoryMo.save(personMo);
    }

    public void personMoDelete(Long id) {
        Optional<PersonMo> person = personRepositoryMo.findById(id);
        if (person.isPresent()) {
            personRepositoryMo.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }
//
    public PersonMo updatePersonMo(Long id, PersonMo person) {
        Optional<PersonMo> addResponse = personRepositoryMo.findById(id);
        if (addResponse.isPresent() && addResponse.get().equals(id)) {
            person.setPersonId(id);
            return personRepositoryMo.save(person);

        } else {
            throw new PersonNotFoundException();
        }
    }
    //MO
    public List<PersonMo> getPersonMo(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<PersonMo> pagedResult = personRepositoryMo.findAll(pageRequest);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<PersonMo>();
        }
    }
}
