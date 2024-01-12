package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원가입(글쓰기)과 회원목록(글 목록) 조회 + 회원(글) 상세조회 + 회원 수정 + 회원 삭제 => CRUD

// 1)회원가입
// 2)회원 목록 조회
    // url: members
    // 화면 : member/memberList
        // => table 형식
            // 이름 이메일 비밀번호
            // td는 적당하게 채워주세요
@Controller
public class MemberController {

    @GetMapping("members")
    public String members(){
        return "member-list";
    }

    @GetMapping("member/create-screen")
    public String createScreen(){
        return "member-create";
    }


}
