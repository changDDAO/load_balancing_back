package com.changddao.load_balancing_back.team.infra.kafka;

import com.changddao.load_balancing_back.member.domain.event.MemberAssignedToTeamEvent;
import com.changddao.load_balancing_back.member.domain.event.MemberCreatedEvent;
import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamEventListener {

    private final TeamRepository teamRepository;

    // 회원 생성 이벤트 구독 (필요 시)
    @KafkaListener(
            topics = "member-created-topic",
            groupId = "team-group"  // 팀 서비스의 고유 그룹 ID
    )
    public void handleMemberCreated(MemberCreatedEvent event) {
        log.info("[팀 서비스] 회원 생성 이벤트 수신: memberId={}, name={}", event.getMemberId(), event.getName());
        // TODO: 회원 생성 시 팀 서비스에서 처리할 로직 (예: 기본 팀 할당)
    }

    // 회원 팀 할당 이벤트 구독 (핵심)
    @KafkaListener(
            topics = "member-assigned-team-topic",
            groupId = "team-group")
    @Transactional
    public void handleMemberAssignedToTeam(MemberAssignedToTeamEvent event) {
        log.info("[팀 서비스] 회원 팀 할당 이벤트 수신: memberId={}, teamId={}", event.getMemberId(), event.getTeamId());

        Team team = teamRepository.findById(event.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("팀이 존재하지 않습니다. teamId=" + event.getTeamId()));

        // 팀에 회원 추가 로직 (예: 팀의 멤버 카운트 증가)
        teamRepository.save(team);
    }
}