package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
// DB에 들어갈 entity 와 사용자에게 전달해줄 정보가 다르다.
// setter를 넣지 않는다.
// -> data를 받는 용도의 DTO클래스를 생성 =

@AllArgsConstructor
// 모든 매개변수를 넣은 생성자

//@NoArgsConstructor
//// 매개변수가 없는 생성자

//@RequiredArgsConstructor
//// 필수값 설정 후, 생성자의 매개변수 설정
public class Member {
    private int id;
    private String name;
    private String email;
    private String pwd;
    private LocalDateTime createTime;
}
