package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public class JdbcMemberRepository implements MemberRepo{

    @Override
    public List<Member> members() {
        return null;
    }

    @Override
    public void memberCreate(Member member) {

    }
    @Override
    public Member memberFind(int id){
        return null;
    }
}
