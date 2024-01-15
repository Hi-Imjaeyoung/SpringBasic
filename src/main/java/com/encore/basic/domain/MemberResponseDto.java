package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {
    private String name;
    private String email;
    private String pwd;
}
