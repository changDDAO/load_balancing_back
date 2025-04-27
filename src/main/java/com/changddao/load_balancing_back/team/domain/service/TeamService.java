package com.changddao.load_balancing_back.team.domain.service;

import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.TeamRepository;
import com.changddao.load_balancing_back.team.domain.dto.MemberAssignedToTeamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void assignMember(MemberAssignedToTeamDto event) {
        log.info("Assign memberId={} to teamId={}", event.getMemberId(), event.getTeamId());

        Team team = teamRepository.findById(event.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + event.getTeamId()));

        log.info("Team '{}' is ready to assign new member (memberId={})", team.getTeamName(), event.getMemberId());
    }

    @Transactional
    public void createTeam(String teamName) {
        Team team = Team.builder()
                .teamName(teamName)
                .build();
        teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Team findTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + id));
    }

    @Transactional
    public void updateTeamName(Long id, String newTeamName) {
        Team team = findTeam(id);
        team.changeTeamName(newTeamName);
    }

    @Transactional
    public void deleteTeam(Long id) {
        Team team = findTeam(id);
        teamRepository.delete(team);
    }
}
