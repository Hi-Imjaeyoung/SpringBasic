package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequest;
import com.encore.basic.domain.MemberResponse;
import com.encore.basic.repository.MemoryRepo;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService =memberService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("create-id")
    public String createScreen(){
        return "create-screen";
    }

    @PostMapping("create-id")
    public String memberPost(MemberRequest memberRequest){
        memberService.save(memberRequest);
        return "redirect:/members";
    }




}
