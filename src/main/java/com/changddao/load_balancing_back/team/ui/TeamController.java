package com.changddao.load_balancing_back.team.ui;

import com.changddao.load_balancing_back.team.domain.Team;
import com.changddao.load_balancing_back.team.domain.dto.MemberAssignedToTeamDto;
import com.changddao.load_balancing_back.team.domain.service.TeamService;
import com.changddao.load_balancing_back.team.ui.dto.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // ✅ 팀 생성
    @PostMapping
    public void createTeam(@RequestBody TeamRequest request) {
        teamService.createTeam(request.teamName());
    }

    // ✅ 모든 팀 조회
    @GetMapping
    public List<Team> findAllTeams() {
        return teamService.findAllTeams();
    }

    // ✅ 단일 팀 조회
    @GetMapping("/{id}")
    public Team findTeam(@PathVariable Long id) {
        return teamService.findTeam(id);
    }

    // ✅ 팀 이름 수정
    @PutMapping("/{id}")
    public void updateTeamName(@PathVariable Long id,
                               @RequestParam String newName) {
        teamService.updateTeamName(id, newName);
    }

    // ✅ 팀 삭제
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

    // ✅ (Kafka Event Listener랑 별개로) 수동으로 멤버를 팀에 할당하는 엔드포인트
    @PostMapping("/{teamId}/assign")
    public void assignMemberManually(@PathVariable Long teamId,
                                     @RequestParam("memberId") Long memberId) {
        MemberAssignedToTeamDto event = new MemberAssignedToTeamDto(memberId, teamId);
        teamService.assignMember(event);
    }
}
