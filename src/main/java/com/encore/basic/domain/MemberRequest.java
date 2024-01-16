package com.encore.basic.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MemberRequest {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private LocalDateTime create_time;
}
