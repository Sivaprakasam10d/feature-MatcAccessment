package com.matc.MATC.AccessMent.Java.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"address"})
@Table(name = "employee")
public class Employee {

    @Id
    private Long empId;

    @Column
    private String experience;

    @Column
    private String designation;

    @Column
    private Long salary;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = true)
    private Person person;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "employee")
//    private List<Address> addressList;

}
