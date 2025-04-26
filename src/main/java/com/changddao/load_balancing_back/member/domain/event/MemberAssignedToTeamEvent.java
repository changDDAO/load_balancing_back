package com.changddao.load_balancing_back.member.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberAssignedToTeamEvent {
    private Long memberId;
    private Long teamId;
}
