package com.changddao.load_balancing_back.team.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberAssignedToTeamDto {
    private Long memberId;
    private Long teamId;
}
