package com.encore.basic.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MemberResponse {
    private int id;
    private final String name;
    private final String email;
    private String pwd;
    private LocalDateTime create_time;
}
