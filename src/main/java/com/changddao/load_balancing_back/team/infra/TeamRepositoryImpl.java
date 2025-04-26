package com.changddao.load_balancing_back.team.infra;

import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.TeamRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final JpaTeamRepository jpaTeamRepository;
    @Override
    public void save(Team team) {
        jpaTeamRepository.save(team);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return jpaTeamRepository.findById(id);
    }
}
