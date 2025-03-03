package com.changddao.load_balancing_back.repository.team;

import com.changddao.load_balancing_back.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    /*@Query("select t from Team t where t.teamName like %:teamName%")*/
    List<Team> findByTeamNameContaining(@Param("teamName")String teamName);

    @Query("select t from Team t left join fetch t.members")
    List<Team> findAllWithMembers();
}
