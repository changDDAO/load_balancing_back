package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import com.changddao.load_balancing_back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;


    @Override
    public Member joinMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Page<MemberDto> findAllWithTeam(MemberSearchDto cond, Pageable pageable) {
        return memberRepository.findAllMemberAndTeamsWithConditions(cond, pageable);
    }

    @Override
    public Page<Member> findAll(MemberSearchDto cond, Pageable pageable) {
        return memberRepository.findAllMembersWithConditions(cond, pageable);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public void changeName(Member member, String name) {
        Optional<Member> findMember = memberRepository.findById(member.getMemberId());
        findMember.orElseThrow(() -> new RuntimeException("찾고자하는 멤버가 없습니다."))
                .changeName(name);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
