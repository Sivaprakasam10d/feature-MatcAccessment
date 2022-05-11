package com.matc.MATC.AccessMent.Java.repository;

import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.response.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee findByEmpId(Long empId);

//    @Query("select new com.matc.MATC.AccessMent.Java.response.EmployeeResponse"
//            + "(emp.empId,emp.experience,emp.designation,"
//            + "emp.salary,per.mobileNumber)"
////            + "brc.closingTime,brc.isMMOutlet,brc.status,ag.agentGroupCode) "
//            + "from Employee emp inner join Person per on per.perId = emp.person")
//    List<EmployeeResponse> findByEmployee();
}

