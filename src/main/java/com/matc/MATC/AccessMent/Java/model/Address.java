package com.matc.MATC.AccessMent.Java.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.matc.MATC.AccessMent.Java.dto.AddressRequestDto;
import com.matc.MATC.AccessMent.Java.dto.EmployeeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@JsonIgnoreProperties({"employee"})
public class Address {
    @Id
    private Long addId;

    @Column
    private String location;

    @Column
    private String landMark;

    @Column
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "empId", nullable = true)
    private Employee employee;



}