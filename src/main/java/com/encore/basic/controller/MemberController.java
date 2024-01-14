package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberDTO;
import com.encore.basic.repository.MemoryRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("members")
    public String members(){
        return "member-list";
    }

    @GetMapping("member/create-screen")
    public String createScreen(){
        return "member/member-create";
    }

    //Param을 다 받아서 처리
    @GetMapping("member/create-check")
    @ResponseBody
    public String memberCreateCheck(@RequestParam(value="name") String name,
                                    @RequestParam(value="id") String id,
                                    @RequestParam(value="pwd") String pwd){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(name);
        memberDTO.setId(id);
        memberDTO.setPwd(pwd);
        MemoryRepo.addMember(new Member(memberDTO));
        return "가입완료";
    }

    // 데이터 바인딩을 사용하여 처리
    @PostMapping("member/create-check2")
    @ResponseBody
    public String memberCreateCheck2(MemberDTO memberDTO){
        MemoryRepo.addMember(new Member(memberDTO));
        return "가입완료";
    }

    @GetMapping("member/show-members")
    @ResponseBody
    public String showMember(){
        return MemoryRepo.showAllMember();
    }



}
