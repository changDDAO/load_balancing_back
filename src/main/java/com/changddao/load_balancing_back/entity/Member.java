package com.changddao.load_balancing_back.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*현재 가정은 한팀에 여러 멤버들이 속할 수 있는 것으로 가정한다.*/
@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;
    /*멤버 이름*/
    private String name;
    /*멤버 나이*/
    private Integer age;
    /*멤버 주소*/
    @Embedded
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @Builder
    public Member(String name,Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /*연관관계 편의메서드*/
    public void setTeam(Team team) {
        /*기존 팀과 관계 제거*/
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        /*양방향 관계 유지*/
        if (team != null && !team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
    /*Member Entity 의 name field값을 변경하기 위한 메서드*/
    public void changeName(String name) {
        this.name = name;
    }
}
