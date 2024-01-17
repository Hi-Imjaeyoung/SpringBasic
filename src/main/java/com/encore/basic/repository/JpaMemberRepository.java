package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepo{

    // EntityManger는 jpa의 핵심 클래스이자 객체이다.
    // Entity의 생명주기를 관리한다. DB와의 모든 상호작용을 책임진다.
    // Entity를 대상 CRUD 기능 제공
    private final EntityManager entityManager;
    @Autowired
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        // persis : 전달된 entity(member)가 EntityManager의 관리 상태가 되도록 만들어주고,
        //          트랜잭션이 커밋될 때 데이터에 저장(insert)
        entityManager.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        // jpql : jpa의 객체지향 쿼리 문법이다.
        // 장정 : DB에 따라 문법이 달라지지 않는 객체지향 언어이다. 컴파일 타임에서 check(Spring data에서)
        // 단점 : DB 고유의 기능과 고유의 성능을 극대화하기는 어렵다
        List<Member> members = entityManager.createQuery("select m from Member m",Member.class)
                                            .getResultList();
        return members;
    }

    @Override
    public Optional<Member> findById(int id) {
        //findMethod는 pk를 매개변수로 준다.
        Member member = entityManager.find(Member.class,id);
        //예외는 서비스에 처리
        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {
        //remove
        //update인 경우 merge
    }

    // pk가 아닌 값으로 조회 할때 select m from Member m.name =:name
//    public List<Member> findByName(String inputName){
//        List<Member> members = entityManager.createQuery("select m from Member m where m.name = :name",Member.class)
//                                            .setParameter("name",inputName)
//                                            .getResultList();
//        return members;
//    }
}
