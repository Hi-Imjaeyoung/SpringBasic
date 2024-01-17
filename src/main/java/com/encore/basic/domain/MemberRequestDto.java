package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 사용자에게 전달 해줄 정보만 가진 DTO 객체를 생성.
// 사용자에게 받을 때, 줄 때
@AllArgsConstructor
@NoArgsConstructor
// DTO 객체들은 생성자를  선언하기 보다는 Setter로 값을 설정하는 것이 더 유연하다.
public class MemberRequestDto {
    private int id;
    private String name;
    private String email;
    private String pwd;
}
