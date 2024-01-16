package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepo memberRepo;

//    // 실습을 위해 구현체로 선언 MemberRepo은 기능이 제한적으로 구현
//    private final SpringDataMemberRepository memberRepo;
    @Autowired
    public MemberService(MybatisMemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }

    // 일반적으로 서비스 단에서 DTO 객체를 받아서 entity로 조립
    public void memberCreate(MemberRequestDto memberDto){
        Member member = new Member(memberDto.getName(),memberDto.getEmail(),memberDto.getPwd());
        memberRepo.save(member);
    }
    public List<MemberResponseDto> members(){
       List<Member> members = memberRepo.findAll();
        // 여러 패턴이 존재
        // setter , 생성자 , builder
       List<MemberResponseDto> memberResponseDtos = members
                                                   .stream()
                                                   .map(a->new MemberResponseDto(a.getId(),a.getName(),a.getEmail(),a.getPwd(),a.getCreateTime()))
                                                   .collect(Collectors.toList());
       return memberResponseDtos;
    }

    // 이거 ResponseDTO로 리턴해라~
    // 무조건 DTO를 써라
                                                // uncheck이긴 하지만  명시해주는 것이 좋다
    public MemberResponseDto memberFind(int id) throws NoSuchElementException{
        Member member = memberRepo.findById(id).orElseThrow(NoSuchElementException::new);
        MemberResponseDto memberResponseDto = null;
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getPwd(), member.getCreateTime());
    }
}
