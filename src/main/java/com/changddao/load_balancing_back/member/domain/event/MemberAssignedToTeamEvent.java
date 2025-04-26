package com.changddao.load_balancing_back.member.domain.event;

import com.changddao.load_balancing_back.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberAssignedToTeamEvent {
    private Long memberId;
    private Long teamId;

    public static MemberAssignedToTeamEvent createBy(Member member) {
        MemberAssignedToTeamEvent event = new MemberAssignedToTeamEvent();
        event.memberId = member.getMemberId();
        event.teamId = member.getTeamId();
        return event;
    }
}
