package com.matc.MATC.AccessMent.Java.response;

import com.matc.MATC.AccessMent.Java.model.Person;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponse {


    private Long empId;

    private String experience;

    private String designation;

    private Long salary;

    private List<Person> person;

}

