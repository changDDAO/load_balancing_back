package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllMembersWithCond(MemberSearchDto cond);
    Page<Member> findAllMembersWithConditions(MemberSearchDto cond, Pageable pageable);
    Page<MemberDto> findAllMemberAndTeamsWithConditions(MemberSearchDto cond, Pageable pageable);
    Member findByNameWithTeam(String name);
}
