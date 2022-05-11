package com.matc.MATC.AccessMent.Java.service;

import com.matc.MATC.AccessMent.Java.dto.EmployeeDto;
import com.matc.MATC.AccessMent.Java.dto.UpdateEmpDto;
import com.matc.MATC.AccessMent.Java.exception.AddressNotFoundException;
import com.matc.MATC.AccessMent.Java.exception.EmployeeNotExistsException;
import com.matc.MATC.AccessMent.Java.exception.PersonNotFoundException;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeDto empCreate(EmployeeDto employeeDto) {

        Person person = personRepository.findByMobileNumber(employeeDto.getMobileNumber());

            Employee employee = new Employee();
            employee.setPerson(person);
            employee.setEmpId(employeeDto.getEmpId());
            employee.setSalary(employeeDto.getSalary());
            employee.setDesignation(employeeDto.getDesignation());
            employee.setExperience(employeeDto.getExperience());

            Address address = new Address();
            address.setAddId(employeeDto.getAddId());
            address.setLocation(employeeDto.getLocation());
            address.setLandMark(employeeDto.getLandMark());
            address.setPinCode(employeeDto.getPinCode());
            address.setEmployee(employee);

            addressRepository.save(address);
            employeeRepository.save(employee);
            return employeeDto;

        }


    public void delete(Long empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isPresent()) {
            employeeRepository.deleteById(empId);
        } else {
            throw new EmployeeNotExistsException();
        }
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent() && employee1.get().getEmpId().equals(id)) {
            employee.setEmpId(id);
            return employeeRepository.save(employee);
        } else {
            throw new AddressNotFoundException();
        }
    }

    public List<Employee> getEmployee(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeRepository.findAll(pageRequest);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }
}
