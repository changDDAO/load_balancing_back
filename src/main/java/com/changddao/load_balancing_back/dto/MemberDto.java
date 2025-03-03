package com.changddao.load_balancing_back.dto;

import com.changddao.load_balancing_back.entity.Address;
import com.changddao.load_balancing_back.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberDto {
    private Long memberId;
    private String name;
    private Address address;
    private Long teamId;
    private String teamName;

    public static MemberDto convertMemberDto(Member member) {
        return new MemberDto(member.getMemberId(), member.getName(), member.getAddress()
                , member.getTeam().getTeamId(), member.getTeam().getTeamName());
    }

}
