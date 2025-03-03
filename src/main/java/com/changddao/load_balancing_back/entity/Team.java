package com.changddao.load_balancing_back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;
    /*팀이름*/
    private String teamName;
    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Member> members = new ArrayList<>();

    @Builder
    public Team(String teamName) {
        this.teamName = teamName;
    }

    /*연관관계 메서드 정의*/
    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }

}
