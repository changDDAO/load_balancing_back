package com.changddao.load_balancing_back.member.domain;

import com.changddao.load_balancing_back.common.global.entity.BaseEntity;
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

    private Long teamId;

    @Builder
    public Member(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * 이름 변경
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * 주소 변경
     */
    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }


}
