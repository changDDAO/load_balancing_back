package com.changddao.load_balancing_back.repository.team;

import com.changddao.load_balancing_back.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByTeamName(String teamName);

}
