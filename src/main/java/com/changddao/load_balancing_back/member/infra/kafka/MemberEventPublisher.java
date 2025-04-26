package com.changddao.load_balancing_back.member.infra.kafka;

import com.changddao.load_balancing_back.member.domain.event.MemberAssignedToTeamEvent;
import com.changddao.load_balancing_back.member.domain.event.MemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishMemberCreated(MemberCreatedEvent event) {
        kafkaTemplate.send("member.created", event);
    }

    public void publishMemberAssignedToTeam(MemberAssignedToTeamEvent event) {
        kafkaTemplate.send("member.assigned-to-team", event);
    }
}
