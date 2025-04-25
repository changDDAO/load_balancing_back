package com.changddao.load_balancing_back.member.domain;

import com.changddao.load_balancing_back.common.global.entity.BaseEntity;
import com.changddao.load_balancing_back.team.domain.Team;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    private String name;
    private Integer age;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Member(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * 이름 변경 (비즈니스 로직으로서 존재)
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * 주소 변경 (추가하면 좋은 메서드)
     */
    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }

    /**
     * 팀 변경 (편의 메서드이지만, 양방향 끊고 단방향 처리 권장)
     */
    public void changeTeam(Team team) {
        this.team = team;
    }
}
