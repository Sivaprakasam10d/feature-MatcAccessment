package com.matc.MATC.AccessMent.Java.controllerTestCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matc.MATC.AccessMent.Java.controller.PersonController;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import com.matc.MATC.AccessMent.Java.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @MockBean
    private PersonService personService;
    private static ObjectMapper mapper = new ObjectMapper();
    @InjectMocks
    private PersonController personController;
    @Autowired
    private WebApplicationContext wac;
    @Mock
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception{

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void PersonCreate() throws Exception {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        when(personRepository.save(person)).thenReturn(person);

        when(personService.createPerson(person)).thenReturn(person);
        String json = mapper.writeValueAsString(person);
        mockMvc.perform(post("/api/v1/person").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.personId", Matchers.equalTo(1l)))
                .andExpect(jsonPath("$.name", Matchers.equalTo("siva")));
    }


    @Test
    public void personListTest() throws Exception {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personService.getAllPerson()).thenReturn(personList);
        mockMvc.perform(get("/api/v1/person")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("siva")));

    }
    @Test
    public void personDelete() throws Exception {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");

        when(personService.personDelete(person.getPersonId())).thenReturn("deleted");
        MvcResult requestResult = mockMvc.perform(delete("/api/v1/person/{id}").param("personId", "1l"))
                .andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertEquals(result, "deleted");
    }
}