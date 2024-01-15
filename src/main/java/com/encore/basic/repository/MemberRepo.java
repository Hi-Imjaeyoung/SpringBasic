package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;

public interface MemberRepo {

    public List<Member> members();

    public void memberCreate(Member member);

    public Member memberFind(int id);
}
