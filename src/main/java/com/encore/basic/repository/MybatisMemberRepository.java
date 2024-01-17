package com.encore.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

// mybatis 레포로 사용하겠다 하는 어노테이션
@Mapper
@Repository
// bean에 2개가 등록되긴 하는데 주입에는 큰 문제가 없으니까 노 프라브럼
public interface MybatisMemberRepository extends MemberRepo {
    // 본문에 MybatisRepository 명세를 정의해야 하나,
    // MemberRepository에서 상속 받고 있으므로, 생략가능
    // 실질적인 커리들 구현은 resources/mapper/MemberMapper.xml 파일에 수행
}
