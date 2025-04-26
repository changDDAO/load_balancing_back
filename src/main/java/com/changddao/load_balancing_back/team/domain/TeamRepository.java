package com.changddao.load_balancing_back.team.domain;

import java.util.Optional;

public interface TeamRepository {
    void save(Team team);

    Optional<Team> findById(Long id);
}
