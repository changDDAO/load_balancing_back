package com.changddao.load_balancing_back.team.domain.service;

import com.changddao.load_balancing_back.team.domain.dto.MemberAssignedToTeamDto;
import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void assignMember(MemberAssignedToTeamDto event) {
        log.info("Assign memberId={} to teamId={}", event.getMemberId(), event.getTeamId());

        // 1. 팀을 찾는다
        Team team = teamRepository.findById(event.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + event.getTeamId()));

        // 2. 여기서는 단순히 log를 찍거나, 팀에 멤버 수를 세거나, 추가 로직을 둘 수 있다
        //    (지금은 실제 Member를 DB에 저장할 수는 없으니, 수신 확인만)

        log.info("Team '{}' is ready to assign new member (memberId={})", team.getTeamName(), event.getMemberId());

        // 실제로 멤버 객체를 저장하거나 관리하려면 Team 엔티티 쪽에 추가 로직이 필요함
    }
}
