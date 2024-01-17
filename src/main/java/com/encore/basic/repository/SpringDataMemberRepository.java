package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Spring 데이터 jpa의 기본 기능을 사용하기 위해서 JapRepository를 상속해야함
// 상속 시 entity명과 해당 entity의 pk타입을 명시
// 구현클래스와 스팩은 simpleJpaRepository class에 있고,
// 실질적인 구동 상황에서 hibernate구현체에 동작을 위임한다.
public interface SpringDataMemberRepository extends MemberRepo,JpaRepository<Member,Integer> {
}
