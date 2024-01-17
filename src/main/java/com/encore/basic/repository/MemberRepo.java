package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {

    public List<Member> findAll();

    public Member save(Member member);
// TODO : int -> long으로 바꿀거여
    public Optional<Member> findById(int id);

    public void delete(Member member);
}
