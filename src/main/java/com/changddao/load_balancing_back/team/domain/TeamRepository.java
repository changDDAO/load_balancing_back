package com.changddao.load_balancing_back.team.domain;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
    void save(Team team);

    Optional<Team> findById(Long id);

    // 모든 팀 조회
    List<Team> findAll();

    // 팀 삭제 (delete는 JpaRepository가 기본 제공하지만 명시적으로 써주고 싶다면 이렇게)
    void delete(Team team);

}
