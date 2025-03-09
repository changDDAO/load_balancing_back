package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import com.changddao.load_balancing_back.service.MemberService;
import com.changddao.load_balancing_back.service.ResponseService;
import com.changddao.load_balancing_back.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final MemberService memberService;
    private final TeamRepository teamRepository;

    @Override
    public String deleteTeam(Long id) {
        Team findTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("찾고자 하는 팀이 없습니다."));

        // 1. Team에 속한 Member가 있으면 IN절로 한 번에 삭제
        if (!findTeam.getMembers().isEmpty()) {
            List<Long> memberIds = findTeam.getMembers().stream()
                    .map(Member::getMemberId)
                    .toList();
            memberService.deleteByIds(memberIds); // IN절로 한 번에 삭제
        }

        // 2. Team 삭제
        teamRepository.delete(findTeam);

        String msg = findTeam.getTeamName() + "이 삭제되었습니다.";
        return msg;

    }
}
