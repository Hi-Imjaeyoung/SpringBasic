package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.repository.MemoryRepo;
import com.encore.basic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 회원가입(글쓰기)과 회원목록(글 목록) 조회 + 회원(글) 상세조회 + 회원 수정 + 회원 삭제 => CRUD

// 1)회원가입
    // url : members/create-screen (get)
    // url : members/create-screen (post)
    // form 태그를 사용

// 2)회원 목록 조회 -> 메모리 DB를 사용
    // url: members
    // 화면 : member/memberList
        // => table 형식
            // 이름 이메일 비밀번호
            // td는 적당하게 채워주세요
@Controller
public class MemberController {
    private final MemberService memberService;

    MemberController(){
        memberService = new MemberService();
    }

    // Controller 는 되도록 간단한 로직만을 실행하도록 만들기
    @GetMapping("members")
    public String members(Model model) {
        model.addAttribute("memberList",memberService.members());
        return "member/member-list";
    }

    @GetMapping("member/create")
    public String createScreen(){
        return "member/member-create";
    }

    @PostMapping("member/create")
    @ResponseBody
    public String memberPost(MemberRequestDto memberRequestDto){
        memberService.memberCreate(memberRequestDto);
        return "ok";
    }

}
