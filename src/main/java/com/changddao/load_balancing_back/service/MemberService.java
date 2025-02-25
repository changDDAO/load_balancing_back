package com.changddao.load_balancing_back.service;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberDto> findAllWithTeam(MemberSearchDto cond, Pageable pageable);

    List<Member> findAll(MemberSearchDto cond, Pageable pageable);

    Optional<Member> findById(Long id);

    void changeName(String name);

    void deleteById(Long id);
}
