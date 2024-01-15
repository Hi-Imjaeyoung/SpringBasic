package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemberRepo;
import com.encore.basic.repository.MemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepo memberRepo;
    @Autowired
    public MemberService(MemoryRepo memoryRepo){
        this.memberRepo = memoryRepo;
    }

    static int totalId = 0;

    // 일반적으로 서비스 단에서 DTO 객체를 받아서 entity로 조립
    public void memberCreate(MemberRequestDto memberDto){
        totalId+=1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Member member = new Member(totalId,memberDto.getName(),memberDto.getEmail(),memberDto.getPwd(),localDateTime);
        memberRepo.memberCreate(member);
    }
    public List<MemberResponseDto> members(){
       List<Member> members = memberRepo.members();
        // 여러 패턴이 존재
        // setter , 생성자 , builder
       List<MemberResponseDto> memberResponseDtos = members
                                                   .stream()
                                                   .map(a->new MemberResponseDto(a.getId(),a.getName(),a.getEmail(),a.getPwd(),a.getCreateTime()))
                                                   .collect(Collectors.toList());
       return memberResponseDtos;
    }
    public Member memberFind(int id){
       return memberRepo.memberFind(id);
    }
}
