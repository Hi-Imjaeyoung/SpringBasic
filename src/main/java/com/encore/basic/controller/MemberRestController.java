package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest")
public class MemberRestController {

    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService =memberService;
    }

    // 전체 회원 조회
    @GetMapping("members")
    public List<MemberResponseDto> members() {
       return memberService.members();
    }

    // 회원 가입
    @PostMapping("member/create")
    public String memberPost(@RequestBody MemberRequestDto memberRequestDto){
        memberService.memberCreate(memberRequestDto);
        return "ok";
    }

    //find
    @GetMapping("member/find/{id}")
    public ResponseEntity<Map<String,Object>> memberFind(@PathVariable int id){
        MemberResponseDto memberResponseDto = null;
        try {
            memberResponseDto = memberService.memberFind(id);
            return ResponseEntityController.responseMessage(HttpStatus.OK,memberResponseDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            //not found error
            return  ResponseEntityController.errorResponseMessage(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }

    //delete
    @DeleteMapping("member/delete/{id}")
    public String memberDelete(@PathVariable int id){
        memberService.memberDelete(id);
        return "ok";
    }

    //update
    @PatchMapping("member/update")
    // TODO : 입력에 대한 예외는 프론트에서 하나요?
    public MemberResponseDto memberUpdate(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.memberUpdate(memberRequestDto);
    }
}
