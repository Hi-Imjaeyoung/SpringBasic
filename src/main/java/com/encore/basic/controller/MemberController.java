package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemoryRepo;
import com.encore.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

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
    // 상세보기 : Param방식으로 보냄
@Controller
// Spring에서 싱글톤으로 만듬 (Spring이 기능 컴포넌트 같은 경우 자동의 관리를 해준다)
// 컨트롤,레포,서비스 어노텐이션을 통해 싱글톤 컴포넌트로 생성 ->스프링 빈으로 등록
// 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control) -> IOC 컨테이너가 스프링빈을 관리
//@RequiredArgsConstructor
public class MemberController {
    //    @Autowired  // 의존성 주입(DI) 방법 1. 필드 주입 방식 : 싱글톤으로 만든 객체를 여기에 주입..?
    //    private MemberService memberService;

    // 의존성 주입(DI) 방법 2. (가장 많이 사용) 생성자 주입방식 : final을 꼭 붙여라!
    // 장점 : final을 통해 상수로 사용가능 (안정성 얻을 수 있다)
    //       다형성 구현 가능 // 싱글톤과 다형성
    //       순환참조 방지
    //       생성자가 한개 밖에 없을 때는 Autowired 생략 가능하다.
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService =memberService;
    }

    // 의존성 주입 방법 3. @RequiredArgsConstructor를 이용한 방식
    //  @RequiredArgsConstructor : @NonNull 어노테이션이 붙어있는 필드 또는 초기화되지 않은 final field 대상으로 생성자 생성.
    //                              => 결국 2번과 같음
//    @Autowired
//    private final MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "member/header";
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
    public String memberPost(MemberRequestDto memberRequestDto){
        memberService.memberCreate(memberRequestDto);
        // url을 redirect
        return "redirect:/members";
    }

    //find
    @GetMapping("member/find")
    public String memberFind(@RequestParam(value="id") int id,Model model){
        try {
            MemberResponseDto member = memberService.memberFind(id);
            model.addAttribute("targetMember",member);
        }catch (NoSuchElementException e){
            return "member/404-error-page";
        }
        return "member/detail";
    }

}
