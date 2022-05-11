package com.matc.MATC.AccessMent.Java.controller;

import com.matc.MATC.AccessMent.Java.MongoModels.EmployeeMo;
import com.matc.MATC.AccessMent.Java.dto.EmployeeDto;
import com.matc.MATC.AccessMent.Java.dto.UpdateEmpDto;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.mongoServices.EmployeeServiceMongo;
import com.matc.MATC.AccessMent.Java.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/api/v1")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeservice;

    @Autowired
    EmployeeServiceMongo employeeServiceMongo;


    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeservice.getEmployeeById(id);
    }

    //Mo
    @GetMapping("/employeeMo/{id}")
    public EmployeeMo getEmployeeMo(@PathVariable("id") Long id) {
        return employeeServiceMongo.getEmployeeMoById(id);
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeservice.getAllEmployee();
    }

    //Mo
    @GetMapping("/EmployeesMo")
    public List<EmployeeMo> getAllMoEmployee() {
        return employeeServiceMongo.getAllEmployeeMo();
    }

    @PostMapping("/employee")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
        var employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeservice.empCreate(employeeDto);

        var employeeMo = new EmployeeMo();                //MO
        BeanUtils.copyProperties(employeeDto, employeeMo);//MO
        employeeServiceMongo.empCreate(employeeDto); //MO
        return employeeDto;
    }


    @PutMapping("/employee/{id}")
    public UpdateEmpDto update(@PathVariable("id") Long id, @RequestBody UpdateEmpDto employeeDto) {
        var employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeservice.updateEmployee(id, employee);

        var employeeMo = new EmployeeMo();                //MO
        BeanUtils.copyProperties(employeeDto, employeeMo);//MO
        employeeServiceMongo.updateEmployee(id, employeeDto);//MO

        return employeeDto;
    }

    @DeleteMapping("/employee/{empId}")
    @PreAuthorize("hasRole('MODERATOR')")
    public void deleteEmployee(@PathVariable("empId") Long empId) {
        employeeservice.delete(empId);
        employeeServiceMongo.delete(empId);
    }

    //Pagination
    @GetMapping("/employee/paging")
    public ResponseEntity<List<Employee>> getEmployee(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "empId") String sortBy) {
        List<Employee> list = employeeservice.getEmployee(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }

//    @GetMapping("/employee/paging")
//    public ResponseEntity<List<EmployeeMo>> getEmployeeMO(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            @RequestParam(defaultValue = "empId") String sortBy) {
//        List<EmployeeMo> list = employeeServiceMongo.getEmployee(pageNo, pageSize, sortBy);
//        return new ResponseEntity<List<EmployeeMo>>(list, HttpStatus.OK);
//    }

}
