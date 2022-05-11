package com.matc.MATC.AccessMent.Java.dto;

import lombok.Data;

@Data
public class UpdateEmpDto {
    private Long empId;
    private String experience;

    private String designation;

    private Long salary;

}
