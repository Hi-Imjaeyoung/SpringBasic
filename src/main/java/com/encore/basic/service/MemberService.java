package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

//    private final MemberRepo memberRepo;

//    // 실습을 위해 구현체로 선언 MemberRepo은 기능이 제한적으로 구현
    private final MemberRepo memberRepo;
    @Autowired
    public MemberService(SpringDataMemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }


    // 전형적인 트랜잭션 작동이 필요한 상황
    // 중간에 예외가 발생했을때 rollback처리 되게
    // 클래스 단위로 붙이면 모든 메서드에 각각 Transactional 적용
    // Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정.
    @Transactional
    // 일반적으로 서비스 단에서 DTO 객체를 받아서 entity로 조립
    public void memberCreate(MemberRequestDto memberDto) throws IllegalArgumentException {
        Member member = new Member(memberDto.getName(),memberDto.getEmail(),memberDto.getPwd());
        memberRepo.save(member);
        // 위 코드는 예외를 잡아서 예외가 터지지 않음 -> rollback이 이루어지지 않음
//        Member member = new Member(memberDto.getName(),memberDto.getEmail(),memberDto.getPwd());
//        memberRepo.save(member);
//        if(member.getName().equals("kim")){
//            throw new IllegalArgumentException();
//        }
    }
    public List<MemberResponseDto> members(){
       List<Member> members = memberRepo.findAll();
        // 여러 패턴이 존재
        // setter , 생성자 , builder
       List<MemberResponseDto> memberResponseDtos = members
                                                   .stream()
                                                   .map(a->new MemberResponseDto(a.getId(),a.getName(),a.getEmail(),a.getPwd(),a.getCreated_time()))
                                                   .collect(Collectors.toList());
       return memberResponseDtos;
    }

    // 이거 ResponseDTO로 리턴해라~
    // 무조건 DTO를 써라
                                                // uncheck이긴 하지만  명시해주는 것이 좋다
    public MemberResponseDto memberFind(int id) throws EntityNotFoundException{
        Member member = memberRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberResponseDto memberResponseDto = null;
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getPwd(), member.getCreated_time());
    }

    public void memberDelete(int id) throws EntityNotFoundException{
        Member targetMember = memberRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepo.delete(targetMember);
    }
    public MemberResponseDto memberUpdate(MemberRequestDto memberRequestDto) {
        // 바로 새  member를 만들면 그 값이 있는지 없는지 검증을 못한다.
        Member member = memberRepo.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        member.setName(memberRequestDto.getName());
        member.setPwd(memberRequestDto.getPwd());
        memberRepo.save(member);
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getPwd(), member.getCreated_time());
    }
}
