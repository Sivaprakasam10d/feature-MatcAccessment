package com.matc.MATC.AccessMent.Java.MongoModels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("address")
public class AddressMo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addId;

    @Column
    private String location;

    @Column
    private String landMark;

    @Column
    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "empId", nullable = false)
    private EmployeeMo employeeMo;


}