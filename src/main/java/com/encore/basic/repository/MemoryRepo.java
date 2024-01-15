package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryRepo implements MemberRepo {
    private final List<Member> memberDB;
    public MemoryRepo(){
       memberDB = new ArrayList<>();
    }
    //Optional객체 사용은 여기서는 안함 원해는 굉장히 중요한 파트

    //일반적으로 DTO 객체는 서비스 단에서 모두 처리
    @Override
    public void memberCreate(Member member){
        memberDB.add(member);
    }

    @Override
    public List<Member> members(){
        return memberDB;
    }

    @Override
    public Member memberFind(int id){
        return memberDB.get(id-1);
    }


}
