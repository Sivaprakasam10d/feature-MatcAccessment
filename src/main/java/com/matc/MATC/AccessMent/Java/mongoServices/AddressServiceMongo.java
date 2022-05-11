package com.matc.MATC.AccessMent.Java.mongoServices;

import com.matc.MATC.AccessMent.Java.MongoModels.AddressMo;
import com.matc.MATC.AccessMent.Java.MongoModels.EmployeeMo;
import com.matc.MATC.AccessMent.Java.dto.AddressRequestDto;
import com.matc.MATC.AccessMent.Java.exception.AddressNotFoundException;
import com.matc.MATC.AccessMent.Java.exception.EmployeeNotExistsException;
import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.mongoRepository.AddressRepositoryMo;
import com.matc.MATC.AccessMent.Java.mongoRepository.EmployeeRepositoryMo;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceMongo {
    @Autowired
    AddressRepositoryMo addressRepositoryMo;

    @Autowired
    EmployeeRepositoryMo employeeRepositoryMo;

    @Autowired
    EmployeeService employeeService;

//    public Address getAddressById(Long id) {
//        Optional<Address> address = addressrepository.findById(id);
//        if (address.isPresent()) {
//            return addressrepository.findById(id).get();
//
//        } else {
//            throw new AddressNotFoundException();
//        }
//    }
//
//    public List<Address> getAllAddress() {
//        return addressrepository.findAll();
//    }
//
//       public List<Address> getAddressByEmployeeId(Long empId){
//           Address address = new Address();
//      addressrepository.findById(empId);
//           return addressrepository.findAll();
//       }
//    public AddressRequestDto addressSave(AddressRequestDto addressRequestDto) {
//        EmployeeMo employeeMo = employeeRepositoryMo.findByEmpId(addressRequestDto.getEmpId());
//        AddressMo addressMo = new AddressMo();
//        addressMo.setEmployeeMo(employeeMo);
//        addressMo.setLocation(addressRequestDto.getLocation());
//        addressMo.setLandMark(addressRequestDto.getLandMark());
//        addressMo.setPinCode(addressRequestDto.getPinCode());
//        addressRepositoryMo.save(addressMo);
//        return addressRequestDto;
//    }
//    public Address save(Long empId, Address address) {
//        Employee employee = employeeService.getEmployeeById(empId);
//        if (employee == null) {
//            throw new EmployeeNotExistsException();
//        } else {
//            Address address1 = new Address();
//            address1.setLocation(address.getLocation());
//            address1.setLandMark(address.getLandMark());
//            address1.setPinCode(address.getPinCode());
//            address1.setEmployee(employee);
//            return addressrepository.save(address);
//        }
//    }

//    public ResponseEntity<List<Address>> deleteByAddress(Long id) {
//        if (!employeeRepository.existsById(id)) {
//            throw new EmployeeNotExistsException();
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//
//    public Address updateAddress(Long id, Address address) throws Exception {
//        Optional<Address> addResponse = addressrepository.findById(id);
//        if (addResponse.isPresent() && addResponse.get().getAddId().equals(id)) {
//            address.setAddId(id);
//            return addressrepository.save(address);
//        } else {
//            throw new AddressNotFoundException();  //Exception
//        }
//
//
//    }
//
    public List<AddressMo> getAddress(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<AddressMo> pagedResult = addressRepositoryMo.findAll(pageRequest);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<AddressMo>();
        }
    }
}
