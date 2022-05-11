package com.matc.MATC.AccessMent.Java.service;

import com.matc.MATC.AccessMent.Java.exception.AddressNotFoundException;
import com.matc.MATC.AccessMent.Java.exception.EmployeeNotExistsException;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressrepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Address getAddressById(Long id) {
        Optional<Address> address = addressrepository.findById(id);
        if (address.isPresent()) {
            return addressrepository.findById(id).get();

        } else {
            throw new AddressNotFoundException();
        }
    }

    public List<Address> getAllAddress() {
        return addressrepository.findAll();
    }

    public List<Address> getAddressByEmployeeId(Long empId) {
        Optional<Address> address = addressrepository.findById(empId);
        if (address.isPresent()) {
            return addressrepository.findAll();
        } else {
            throw new EmployeeNotExistsException();
        }

    }

    public ResponseEntity<Address> addressSave(Long empId, Address addressReq) {
        Address address = employeeRepository.findById(empId).map(employee -> {
            addressReq.setEmployee(employee);
            return addressrepository.save(addressReq);
        }).orElseThrow(() -> new EmployeeNotExistsException());
        return new ResponseEntity<>(address, HttpStatus.CREATED);

    }

    public void deleteByAddress(Long id) {
        Optional<Address> address = addressrepository.findById(id);
        if (address.isPresent()) {
            addressrepository.deleteById(id);
        } else {
            throw new AddressNotFoundException();
        }
    }
    public Address updateAddress(Long id, Address address) throws Exception {
        Optional<Address> addResponse = addressrepository.findById(id);
        if (addResponse.isPresent() && addResponse.get().getAddId().equals(id)) {
            address.setAddId(id);
            return addressrepository.save(address);
        } else {
            throw new AddressNotFoundException();  //Exception
        }


    }

    public List<Address> getAddress(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Address> pagedResult = addressrepository.findAll(pageRequest);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Address>();
        }
    }

    public ResponseEntity<List<Address>> getAddByEmp(Long empId) {
        if (!employeeRepository.existsById(empId)) {
            throw new EmployeeNotExistsException();
        }
        List<Address> comments = addressrepository.findByEmpId(empId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    public void deleteByAddressBy(Long id) {
        Optional<Address> address = addressrepository.findById(id);
        if (address.isPresent()) {
            addressrepository.deleteById(id);
        } else {
            throw new EmployeeNotExistsException();
        }
    }
}
