package com.matc.MATC.AccessMent.Java.serviceTestCase;

import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import com.matc.MATC.AccessMent.Java.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
public class PersonServiceTestCase {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;


    @Test
    public void createPersonTest() {
        Person person = new Person();
        person.setPersonId(1l);
        person.setName("siva");
        person.setMobileNumber("1234567890");
        person.setEmail("siva@gmail.com");

        personService.createPerson(person);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void getAllPersonTest() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person(1l, "siva", "siva@gmail.com", "1234567890");
        Person person2 = new Person(2l, "java", "siva@gmail.com", "234567890");

        personList.add(person);
        personList.add(person2);
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> person1 = personService.getAllPerson();
        assertEquals(2, person1.size());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void getPersonById() {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        when(personRepository.findById(1l)).thenReturn(Optional.of(person));

        person = personService.getPersonById(person.getPersonId());
        assertEquals("siva", person.getName());
        assertEquals("234567890", person.getMobileNumber());
    }

    @Test
    public void personDelete() {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        when(personRepository.findById(person.getPersonId())).thenReturn(Optional.of(person));
        personService.personDelete(person.getPersonId());
        verify(personRepository, times(1)).deleteById(person.getPersonId());
    }

    @Test
    public void updatePerson() {

        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        given(personRepository.save(person)).willReturn(person);

        when(personRepository.findById(person.getPersonId())).thenReturn(Optional.of(person));
        person.setPersonId(1l);
        person.setName("java");
        person.setEmail("siva@gmail");
        person.setMobileNumber("123456");
        person = personRepository.findById(1L).get();
        personRepository.save(person);

        person = personService.updatePerson(person.getPersonId(), person);
        assertThat(person.getEmail()).isEqualTo("siva@gmail");
        assertThat(person.getName()).isEqualTo("java");
    }

}
