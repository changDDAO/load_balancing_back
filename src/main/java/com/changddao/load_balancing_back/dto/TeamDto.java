package com.changddao.load_balancing_back.dto;

import com.changddao.load_balancing_back.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamDto {
    private Long id;
    private String teamName;
    private List<MemberDto> members;

    public static TeamDto convertTeamDto(Team team){
        return new TeamDto(team.getTeamId(),
                team.getTeamName(),
                team.getMembers().stream().map(MemberDto::convertMemberDto).collect(Collectors.toList()));
    }

}
