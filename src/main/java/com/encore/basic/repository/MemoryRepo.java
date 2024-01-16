package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryRepo implements MemberRepo{
    private final List<Member> members;

    public MemoryRepo(){
        members = new ArrayList<>();
    }

    @Override
    public Member save(Member member) {
        members.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        Member member = null;
        for(Member now : members){
            if(now.getId()==id){
                member = now;
            }
        }
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return members;
    }
}
