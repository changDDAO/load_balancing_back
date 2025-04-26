package com.changddao.load_balancing_back.team.infra;

import com.changddao.load_balancing_back.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeamRepository extends JpaRepository<Team, Long> {
}
