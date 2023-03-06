package com.group.demchenkotest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseDto extends BaseResponse {
    private String firstName;
    private String lastName;
    private Integer age;

}
