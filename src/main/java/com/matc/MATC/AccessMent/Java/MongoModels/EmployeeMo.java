package com.matc.MATC.AccessMent.Java.MongoModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matc.MATC.AccessMent.Java.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("employee")
public class EmployeeMo {

    @Id
    private Long empId;

    @Column
    private String experience;

    @Column
    private String designation;

    @Column
    private Long salary;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String role;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", nullable = false)
    PersonMo personMo;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList<>();




}
