package com.matc.MATC.AccessMent.Java.mongoServices;

import com.matc.MATC.AccessMent.Java.MongoModels.AddressMo;
import com.matc.MATC.AccessMent.Java.MongoModels.EmployeeMo;
import com.matc.MATC.AccessMent.Java.MongoModels.PersonMo;
import com.matc.MATC.AccessMent.Java.dto.EmployeeDto;
import com.matc.MATC.AccessMent.Java.dto.UpdateEmpDto;
import com.matc.MATC.AccessMent.Java.exception.AddressNotFoundException;
import com.matc.MATC.AccessMent.Java.exception.EmployeeNotExistsException;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.mongoRepository.AddressRepositoryMo;
import com.matc.MATC.AccessMent.Java.mongoRepository.EmployeeRepositoryMo;
import com.matc.MATC.AccessMent.Java.mongoRepository.PersonRepositoryMo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceMongo {
    @Autowired
    EmployeeRepositoryMo employeeRepositoryMo;
    @Autowired
    PersonRepositoryMo personRepositoryMo;
    @Autowired
    AddressRepositoryMo addressRepositoryMo;


    public EmployeeMo getEmployeeMoById(Long id) {
        return employeeRepositoryMo.findById(id).get();

    }

    public EmployeeDto getEmployeeAddressById(Long id) {
        EmployeeDto employeeDto = new EmployeeDto();

        EmployeeMo employeeMo = employeeRepositoryMo.findById(id).get();
        employeeDto.setExperience(employeeMo.getExperience());
        employeeDto.setDesignation(employeeMo.getDesignation());
        employeeDto.setSalary(employeeMo.getSalary());

        return employeeDto;

    }

    public List<EmployeeMo> getAllEmployeeMo() {
        return employeeRepositoryMo.findAll();
    }

    public EmployeeDto empCreate(EmployeeDto employeeDto) {
        PersonMo personMo = personRepositoryMo.findByMobileNumber(employeeDto.getMobileNumber());
        EmployeeMo employeeMo = new EmployeeMo();
        employeeMo.setPersonMo(personMo);
        employeeMo.setEmpId(employeeDto.getEmpId());
        employeeMo.setSalary(employeeDto.getSalary());
        employeeMo.setDesignation(employeeDto.getDesignation());
        employeeMo.setExperience(employeeDto.getExperience());
        AddressMo addressMo = new AddressMo();

        addressMo.setAddId(employeeDto.getAddId());
        addressMo.setLocation(employeeDto.getLocation());
        addressMo.setLandMark(employeeDto.getLandMark());
        addressMo.setPinCode(employeeDto.getPinCode());
        addressMo.setEmployeeMo(employeeMo);

        addressMo = addressRepositoryMo.save(addressMo);
        employeeRepositoryMo.save(employeeMo);
        return employeeDto;
    }


    public void delete(Long id) {
        Optional<EmployeeMo> employee = employeeRepositoryMo.findById(id);
        if (employee.isPresent()) {
            employeeRepositoryMo.deleteById(id);

        } else {
            throw new EmployeeNotExistsException();
        }
    }

    public EmployeeMo updateEmployee(Long id, UpdateEmpDto employeeDto) {
        Optional<EmployeeMo> employee2 = employeeRepositoryMo.findById(id);
        if (employee2.isPresent() && employee2.get().getEmpId().equals(id)) {
            EmployeeMo employeeMo= new EmployeeMo();
            employeeMo.setEmpId(employeeDto.getEmpId());
            employeeMo.setSalary(employeeDto.getSalary());
            employeeMo.setDesignation(employeeDto.getDesignation());
            employeeMo.setExperience(employeeDto.getExperience());
            return employeeRepositoryMo.save(employeeMo);
        } else {
            throw new AddressNotFoundException();
        }
    }

    public List<EmployeeMo> getEmployee(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeMo> pagedResult = employeeRepositoryMo.findAll(pageRequest);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<EmployeeMo>();
        }
    }
}