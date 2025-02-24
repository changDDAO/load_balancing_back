package com.changddao.load_balancing_back.repository.team;

import com.changddao.load_balancing_back.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
