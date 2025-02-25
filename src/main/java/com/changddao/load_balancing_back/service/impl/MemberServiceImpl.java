package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import com.changddao.load_balancing_back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public List<MemberDto> findAllWithTeam(MemberSearchDto cond, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<Member> findAll(MemberSearchDto cond, Pageable pageable) {
        return List.of();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void changeName(String name) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
