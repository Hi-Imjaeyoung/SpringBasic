package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequest;
import com.encore.basic.domain.MemberResponse;
import com.encore.basic.repository.MemberRepo;
import com.encore.basic.repository.MemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    private final MemberRepo memberRepo;
    @Autowired
    public MemberService(MemoryRepo memberRepo){
       this.memberRepo = memberRepo;
    }
    //TODO : 여기서 입력 받은 값을 다시 전달 할때는 ResponseDTO를 사용해야 할까용
    public MemberResponse save(MemberRequest memberRequest){
        LocalDateTime now = LocalDateTime.now();
        Member member = new Member(memberRequest.getId(),memberRequest.getName(),memberRequest.getEmail(), memberRequest.getPwd(),now);
        memberRepo.save(member);
        return new MemberResponse(member.getName(),member.getEmail());
    }
}
