package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {

    //@ResponseStatus 어노테이션 방식
    // 상태코드만 헤더에 넣어주는 방식
    @GetMapping("responsestatus1")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus1(){
        return "OK";
    }

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        Member member = new Member();
        return member;
    }

    //ResponseEntity 객체를 직접 생성하는 방식
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member();
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @GetMapping("custom2")
    // 의미가 없다
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 ID 입니다</h1>";
        return new ResponseEntity<>(html,HttpStatus.NOT_FOUND);
    }

    //map형태의 message custom
    //예외 처리 상황
    public static ResponseEntity<Map<String,Object>> errorResponseMessage(HttpStatus status,String message){
        Map<String,Object> body = new HashMap<>();
        body.put("status",String.valueOf(status.value()));
        body.put("error message",message);
                                    //본문 // 헤더
        return new ResponseEntity<>(body,status);
    }

    // 정상처리 상황
    // status : 201. message : 객체
    public static ResponseEntity<Map<String,Object>> responseMessage(HttpStatus status, MemberResponseDto member){
        Map<String,Object> body = new HashMap<>();
        body.put("status",String.valueOf(status.value()));
        body.put("message",member);
        //본문 // 헤더
        return new ResponseEntity<>(body,status);
    }

    // 메서드 체이닝 : ResponseEntity의 클래스 메서드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("재영","이메일","비번");
        return  ResponseEntity.ok(member);
    }

    @GetMapping("chaining2")
    public ResponseEntity<Member> chaining2(){
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member("재영","이메일","비번");
        return  ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

}
