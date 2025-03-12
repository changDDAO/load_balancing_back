package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.TeamDto;
import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import com.changddao.load_balancing_back.service.ResponseService;
import com.changddao.load_balancing_back.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class TeamController {
    private final ResponseService responseService;
    private final TeamService teamService;
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
    /*팀 이름 변경*/
    @PutMapping("/v1/team/{id}")
    public SingleResult<TeamDto> changTeamName(@PathVariable("id") Long id, @RequestBody TeamDto dto) {
        Team findTeam = repository.findById(id).orElseThrow(() -> new RuntimeException("찾고자 하는 팀이 없습니다."));
        findTeam.changeTeamName(dto.getTeamName());
        return responseService.handleSingleResult(TeamDto.convertTeamDto(findTeam));
    }
    @PostMapping("v1/team/{id}")
    public SingleResult<TeamDto> onlyTeam(@PathVariable("id") Long id){
        Team findTeam = repository.findByIdWithOutMembers(id).orElseThrow(() -> new RuntimeException("찾고자 하는 팀이 없습니다."));
        return responseService.handleSingleResult(new TeamDto(findTeam.getTeamId(), findTeam.getTeamName(), null));
    }
    /*팀삭제 api 단, 팀에 소속된 멤버들이 Null인 경우, 즉시 삭제가 가능하나, team을 참조하고 있는 member들이
    * 있다면, 멤버 먼저 삭제*/
    @DeleteMapping("v1/team/{id}")
    public SingleResult<String> deleteTeam(@PathVariable("id") Long id) {
        String msg = teamService.deleteTeam(id);
        return responseService.handleSingleResult(msg);
    }

}
