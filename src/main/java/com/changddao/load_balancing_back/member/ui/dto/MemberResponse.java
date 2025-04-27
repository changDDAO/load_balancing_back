package com.changddao.load_balancing_back.member.ui.dto;

import com.changddao.load_balancing_back.member.domain.Address;
import com.changddao.load_balancing_back.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long memberId;
    private String name;
    private Integer age;
    private Address address;
    private Long teamId;

    public static MemberResponse from(Member member) {
        MemberResponse response = new MemberResponse();
        response.memberId = member.getMemberId();
        response.name = member.getName();
        response.age = member.getAge();
        response.address = member.getAddress();
        response.teamId = member.getTeamId();
        return response;
    }
}