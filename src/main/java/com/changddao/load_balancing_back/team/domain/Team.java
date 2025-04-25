package com.changddao.load_balancing_back.team.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;

    private String teamName;

    @Builder
    public Team(String teamName) {
        this.teamName = teamName;
    }

    /**
     * 팀 이름 변경 (의미 있는 행위 메서드)
     */
    public void changeTeamName(String name) {
        this.teamName = name;
    }
}
