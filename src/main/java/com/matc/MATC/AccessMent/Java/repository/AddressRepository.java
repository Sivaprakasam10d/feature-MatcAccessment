package com.matc.MATC.AccessMent.Java.repository;

import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.response.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select new com.matc.MATC.AccessMent.Java.response.AddressResponse"
            + "(add.addId,add.location,add.landMark,add.pinCode,"
            + "emp.empId) "
            + "from Address add inner join Employee emp on emp.empId = add.employee")
    List<AddressResponse> listOfAddress();

//    @Query(value = "select * FROM MATC_AssessMent.address where emp_id=?1",nativeQuery = true)
    @Query(value = "select employee.emp_id,employee.designation,employee.experience,employee.salary,address.add_id,address.land_mark,address.location,address.pin_code from employee \n" +
            "left join address on employee.emp_id =address.emp_id where employee.emp_id=?1",nativeQuery = true)
    List<Address> findByEmpId(Long empId);
}


