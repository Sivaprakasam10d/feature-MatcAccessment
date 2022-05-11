package com.matc.MATC.AccessMent.Java.controller;

import com.matc.MATC.AccessMent.Java.MongoModels.PersonMo;
import com.matc.MATC.AccessMent.Java.dto.PersonDto;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.mongoServices.PersonServiceMongo;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import com.matc.MATC.AccessMent.Java.service.PersonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/api/v1")
public class PersonController {
    @Autowired
    PersonService personService;
    @Autowired
    PersonServiceMongo personServiceMongo;

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    //Mo
    @GetMapping("/personMo/{id}")
    public PersonMo getPersonMo(@PathVariable("id") Long id) {
        return personServiceMongo.getPersonMoById(id);
    }

    @GetMapping("/person")
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    //Mo
    @GetMapping("/personMo")
    public List<PersonMo> getAllPersonMo() {
        return personServiceMongo.getAllPersonMo();
    }

    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.personDelete(id);
        personServiceMongo.personMoDelete(id);
        return "deleted";
    }
    @PostMapping("/person")
    public PersonDto savePerson(@RequestBody PersonDto personDto) {

        var person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personService.createPerson(person);

        var personMo = new PersonMo();               //MO
        BeanUtils.copyProperties(personDto, personMo);//MO
        personServiceMongo.save(personMo);
        return personDto;
    }

    @PutMapping("/person/{id}")
    public PersonDto updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        var person = new Person();
        BeanUtils.copyProperties(personDto, person);
         personService.updatePerson(id, person);

        var personMo = new PersonMo();               //MO
        BeanUtils.copyProperties(personDto, personMo);//MO
        personServiceMongo.save(personMo);
        return personDto;
    }


    //Pagination
    @GetMapping("/person/paging")
    public ResponseEntity<List<Person>> getPerson(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Person> list = personService.getPersonPagination(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    //MO
    @GetMapping("/personMo/paging")
    public ResponseEntity<List<PersonMo>> getPersonMo(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<PersonMo> personMos = personServiceMongo.getPersonMo(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<PersonMo>>(personMos, HttpStatus.OK);
    }


}