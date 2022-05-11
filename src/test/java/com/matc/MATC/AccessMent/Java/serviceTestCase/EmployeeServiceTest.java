package com.matc.MATC.AccessMent.Java.serviceTestCase;

import com.matc.MATC.AccessMent.Java.dto.EmployeeDto;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import com.matc.MATC.AccessMent.Java.service.EmployeeService;
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
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void employeeCreateTest() {

        //Person
        Person person = new Person();
        person.setPersonId(1l);
        person.setName("siva");
        person.setEmail("siva@gmail.com");
        person.setMobileNumber("9988776655");

        // EmployeeDto
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpId(1l);
        employeeDto.setExperience("one");
        employeeDto.setDesignation("javaDev");
        employeeDto.setMobileNumber("9988776655");
        employeeDto.setAddId(1l);
        employeeDto.setLocation("chennai");
        employeeDto.setLandMark("porur");
        employeeDto.setPinCode("909090");
        employeeDto.setSalary(20l);

        when(personRepository.findByMobileNumber(employeeDto.getMobileNumber())).thenReturn(person);
        person = personRepository.findByMobileNumber(employeeDto.getMobileNumber());
        //Employee
        Employee employee = new Employee();
        employee.setEmpId(employeeDto.getEmpId());
        employee.setSalary(employeeDto.getSalary());
        employee.setExperience(employeeDto.getExperience());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setPerson(person);
        //Address
        Address address = new Address();
        address.setAddId(employeeDto.getAddId());
        address.setLocation(employeeDto.getLocation());
        address.setLandMark(employeeDto.getLandMark());
        address.setPinCode(employeeDto.getPinCode());
        address.setEmployee(employee);
        employeeDto = employeeService.empCreate(employeeDto);
        verify(addressRepository, times(1)).save(any());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    public void getAllEmployeeTest() {
        List<Employee> employeeList = new ArrayList<>();
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        Employee employee2 = new Employee(1l, "one", "java", 12l, person);
        employeeList.add(employee);
        employeeList.add(employee2);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employees = employeeService.getAllEmployee();
        assertEquals(2, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest() {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        when(employeeRepository.findById(1l)).thenReturn(Optional.of(employee));
        employee = employeeService.getEmployeeById(employee.getEmpId());
        assertEquals("java", employee.getDesignation());
        assertEquals(12l, employee.getSalary());
    }

    @Test
    public void employeeDeleteTest() {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        employeeService.delete(employee.getEmpId());
        verify(employeeRepository, times(1)).deleteById(employee.getEmpId());
    }

    @Test
    public void updateEmployeeTest() {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        given(employeeRepository.save(employee)).willReturn(employee);

        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
        employee.setEmpId(1l);
        employee.setExperience("two");
        employee.setDesignation("jav");
        employee.setSalary(20l);
        employee = employeeRepository.findById(1L).get();
        employeeRepository.save(employee);

        employee = employeeService.updateEmployee(employee.getEmpId(), employee);
        assertThat(employee.getDesignation()).isEqualTo("jav");
        assertThat(employee.getExperience()).isEqualTo("two");
    }
}
