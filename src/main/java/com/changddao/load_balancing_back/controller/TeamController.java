package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.TeamDto;
import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import com.changddao.load_balancing_back.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Transactional
public class TeamController {
    private final ResponseService responseService;
    private final TeamRepository repository;
    @GetMapping("/v1/teams")
    public MultipleResult<Team> getTeams(){
        return responseService.handleListResult(repository.findAllWithMembers());
    }
    @GetMapping("/v1/team/{id}")
    public SingleResult<TeamDto> getTeamWithMember(@PathVariable("id") Long id) {
        Team team = repository.findByIdWithMembers(id)
                .orElseThrow(() -> new RuntimeException("찾고자하는 팀이없습니다."));
        SingleResult<TeamDto> teamDtoSingleResult = responseService.handleSingleResult(TeamDto.convertTeamDto(team));
        return responseService.handleSingleResult(TeamDto.convertTeamDto(team));
    }

}
