package com.changddao.load_balancing_back.member.domain.service;

import com.changddao.load_balancing_back.member.domain.Member;
import com.changddao.load_balancing_back.member.domain.MemberRepository;
import com.changddao.load_balancing_back.member.domain.event.MemberAssignedToTeamEvent;
import com.changddao.load_balancing_back.member.domain.event.MemberCreatedEvent;
import com.changddao.load_balancing_back.member.infra.kafka.MemberEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberEventPublisher eventPublisher;

    @Transactional
    public void createMember(Member member) {
        memberRepository.save(member);
        eventPublisher.publishMemberCreated(MemberCreatedEvent.createBy(member));
    }

    @Transactional
    public void changeMemberName(Long id, String newName) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
        member.changeName(newName);
    }

    @Transactional
    public void changeTeam(Long memberId, Long teamId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버 없음"));
        member.changeTeamId(teamId); // 도메인에 teamId 넣는 방식
        eventPublisher.publishMemberAssignedToTeam(MemberAssignedToTeamEvent.createBy(member));
    }

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("멤버 없음"));
    }
}

