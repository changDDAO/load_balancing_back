package com.changddao.load_balancing_back.team.infra;

import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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

    @Override
    public List<Team> findAll() {
        return jpaTeamRepository.findAll();
    }

    @Override
    public void delete(Team team) {
        jpaTeamRepository.delete(team);
    }
}
