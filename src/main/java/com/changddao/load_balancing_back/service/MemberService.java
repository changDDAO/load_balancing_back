package com.changddao.load_balancing_back.service;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    /*leftJoin을 이용하여 Dto에 원하는 값만 mapping*/
    Page<MemberDto> findAllWithTeam(MemberSearchDto cond, Pageable pageable);
    /*fetchjoin을 사용하여 연관관계를 가진 Entity까지 전부 조회하기*/
    Page<Member> findAll(MemberSearchDto cond, Pageable pageable);
    /*멤버 저장*/
    Member joinMember(Member member);
    Optional<Member> findById(Long id);
    /*멤버 이름변경*/
    void changeName(Member member, String name);
    /*멤버 삭제*/
    void deleteById(Long id);

    /*멤버 여러명 삭제*/
    void deleteByIds(List<Long> memberIds);
}
