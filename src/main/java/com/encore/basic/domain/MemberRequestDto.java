package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 사용자에게 전달 해줄 정보만 가진 DTO 객체를 생성.
// 사용자에게 받을 때, 줄 때
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String pwd;
}
