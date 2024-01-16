package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {
    Member save(Member member);

    Optional<Member> findById(int id);

    List<Member> findAll();

}
