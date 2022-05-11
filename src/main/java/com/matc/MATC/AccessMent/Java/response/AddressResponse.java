package com.matc.MATC.AccessMent.Java.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long addId;

    private String location;

    private String landMark;

    private String pinCode;

    private Long empId;
}
