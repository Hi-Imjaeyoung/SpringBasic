package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryRepo implements MemberRepo {
    private final List<Member> memberDB;
    static int totalId = 0;
    public MemoryRepo(){
       memberDB = new ArrayList<>();
    }
    //Optional객체 사용은 여기서는 안함 원해는 굉장히 중요한 파트

    //일반적으로 DTO 객체는 서비스 단에서 모두 처리
    @Override
    public Member save(Member member){
        totalId+=1;
        LocalDateTime localDateTime = LocalDateTime.now();
        member.setId(totalId);
        member.setCreated_time(localDateTime);
        memberDB.add(member);
        return member;
    }

    @Override
    public List<Member> findAll(){
        return memberDB;
    }

    @Override
    public Optional<Member> findById(int id){
        for(Member now : memberDB){
            if(now.getId()==id){
                return Optional.of(now);
            }
        }
         return Optional.empty();
    }
    @Override
    public void delete(Member member) {

    }
}
