package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<Member> findAllMembersWithConditions(MemberSearchDto cond, Pageable pageable);
    Page<MemberDto> findAllMemberAndTeamsWithConditions(MemberSearchDto cond, Pageable pageable);
}
