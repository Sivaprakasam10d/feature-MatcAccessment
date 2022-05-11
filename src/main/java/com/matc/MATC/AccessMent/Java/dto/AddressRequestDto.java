package com.matc.MATC.AccessMent.Java.dto;

import com.matc.MATC.AccessMent.Java.model.Employee;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    private Long addId;

    private String location;

    private String landMark;

    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "empId", nullable = true)
    private Employee employee;

}
