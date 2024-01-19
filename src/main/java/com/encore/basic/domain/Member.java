package com.encore.basic.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
// DB에 들어갈 entity 와 사용자에게 전달해줄 정보가 다르다.
// setter를 넣지 않는다.
// -> data를 받는 용도의 DTO클래스를 생성 =

//@AllArgsConstructor
// 모든 매개변수를 넣은 생성자

//@NoArgsConstructor
//// 매개변수가 없는 생성자

//@RequiredArgsConstructor
//// 필수값 설정 후, 생성자의 매개변수 설정

@Entity
// Entity를 통해 mariadb의 테이블 및 컬럼을 자동 생성 대상이된다.
// 조회 할때 리플렉션을 통해 사용
// 클래스명 > 테이블명, 변수명 컬럼명
// 만약 생성자가 존재하면 기본생성자가 필요
@NoArgsConstructor
public class Member {
    @Setter
    //이건 어쩔수 없다. 메모리 DB 때문
    @Id
    //identity = auto_increment 설정 , auto = jpa가 자동으로 적절한 키 생성 전략
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    // String = varchar로 변환
    private String name;

    @Column(nullable = false,length = 50)
    private String email;

    @Setter
    private String pwd;

    @Setter
    @Column(name = "created_time") // name 옵션을 통해 DB 컬럼명 별도로 지정가능
    @CreationTimestamp
    private LocalDateTime created_time;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Member(String name, String email,String pwd){
        this.name =name;
        this.email =email;
        this.pwd = pwd;
    }

    public void updateMember(String name,String pwd){
        this.name = name;
        this.email= pwd;
    }
    public static void test(){};
}
