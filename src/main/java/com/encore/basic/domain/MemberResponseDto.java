package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberResponseDto {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private LocalDateTime createTime;
}