package com.changddao.load_balancing_back.team.infra.kafka;

import com.changddao.load_balancing_back.team.domain.service.TeamService;
import com.changddao.load_balancing_back.team.domain.dto.MemberAssignedToTeamDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamEventListener {

    private final TeamService teamService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "member-assigned-team-topic", groupId = "team-group")
    public void handleMemberAssignedToTeam(String message) {
        try {
            log.info("Received message: {}", message);
            MemberAssignedToTeamDto event = objectMapper.readValue(message, MemberAssignedToTeamDto.class);

            // 서비스 로직 호출 (TeamService 등)
            teamService.assignMember(event);

        } catch (Exception e) {
            log.error("Failed to process message", e);
        }
    }
}
