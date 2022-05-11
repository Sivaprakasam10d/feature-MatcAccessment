package com.matc.MATC.AccessMent.Java.dto;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long personId;

    private String name;

    private	String email;

    private	String mobileNumber;


}
