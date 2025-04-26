package com.changddao.load_balancing_back.member.domain.event;

import com.changddao.load_balancing_back.member.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreatedEvent {
    private Long memberId;
    private String name;
    private Integer age;
    private Address address;
}
