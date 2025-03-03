package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import com.changddao.load_balancing_back.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Transactional
public class TeamController {
    private final ResponseService responseService;
    private final TeamRepository repository;
    @GetMapping("v1/teams")
    public MultipleResult<Team> getTeams(){
        return responseService.handleListResult(repository.findAllWithMembers());
    }
}
