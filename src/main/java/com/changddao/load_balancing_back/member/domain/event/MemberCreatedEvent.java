package com.changddao.load_balancing_back.member.domain.event;

import com.changddao.load_balancing_back.member.domain.Address;
import com.changddao.load_balancing_back.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreatedEvent {
    private Long memberId;
    private String name;
    private Integer age;
    private Address address;

    public static MemberCreatedEvent createBy(Member member) {
        MemberCreatedEvent event = new MemberCreatedEvent();
        event.memberId = member.getMemberId();
        event.name = member.getName();
        event.age = member.getAge();
        event.address = member.getAddress();
        return event;
    }
}
