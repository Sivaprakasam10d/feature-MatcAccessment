package com.matc.MATC.AccessMent.Java.controller;

import com.matc.MATC.AccessMent.Java.MongoModels.AddressMo;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.mongoServices.AddressServiceMongo;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.service.AddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    AddressService addressservice;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AddressServiceMongo addressServiceMongo;

    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable("id") Long id) {
        return addressservice.getAddressById(id);
    }
    @GetMapping("/employee/{empId}/address")
    public ResponseEntity<List<Address>> getAllCommentsByTutorialId(@PathVariable(value = "empId") Long empId) {
        return addressservice.getAddByEmp(empId);

    }

    @GetMapping("/address")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Address> getAllAddress() {
        return addressservice.getAllAddress();
    }

    @GetMapping("/addresses/employee/{empId}")
    public List<Address> getAddressByEmployeeId(@PathVariable("empId") Long empId) {
        return addressservice.getAddressByEmployeeId(empId);
    }
    @PostMapping("/employee/{empId}/addresses")
    public ResponseEntity<Address> createAddress(@PathVariable(value = "empId") Long empId,
                                                 @RequestBody Address address)
    {
        addressservice.addressSave(empId,address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/employee/{empId}/address")
    @PreAuthorize("hasRole('MODERATOR')")
    public void deleteAddressEmpId(@PathVariable("empId") Long id) {
        addressservice.deleteByAddress(id);
    }
    @DeleteMapping("/address")
    public void deleteAddress(@PathVariable("empId") Long id) {
      addressservice.deleteByAddressBy(id);
    }



    @PutMapping("/address/{id}")
    public Address update(@PathVariable("id") Long id, @RequestBody Address address) throws Exception {
        return addressservice.updateAddress(id, address);
    }
    //Pagination
    @GetMapping("address/paging")
    public ResponseEntity<List<Address>> getAddress(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Address> list = addressservice.getAddress(pageNo, pageSize, sortBy);
        List<AddressMo> addressMo = addressServiceMongo.getAddress(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Address>>(list, HttpStatus.OK);
    }
}

