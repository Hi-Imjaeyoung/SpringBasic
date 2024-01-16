package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private LocalDateTime create_time;

}
