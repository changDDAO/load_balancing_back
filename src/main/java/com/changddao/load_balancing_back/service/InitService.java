package com.changddao.load_balancing_back.service;

import com.changddao.load_balancing_back.entity.Address;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitService {
    private final MemberRepository repository;
    private static final Faker faker = new Faker(new Locale("ko"));
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @PostConstruct
    public void initDummyData() {
        /*db에 더미 데이터가 들어있는 지 checing*/
        if (memberRepository.count() > 0) {
            log.info("이미 Member 더미데이터가 있습니다. ");
            return;
        }
        if (teamRepository.count() > 0) {
            log.info("이미 Team 더미데이터가 있습니다. ");
            return;
        }

        List<Member> members = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            members.add(Member.builder()
                    .name(faker.name().fullName()) // 랜덤한 이름 생성
                    .age(faker.number().numberBetween(20, 60)) // 20~60 사이의 나이
                    .address(new Address(
                            faker.address().city(),
                            faker.address().streetAddress(),
                            faker.address().zipCode()
                    ))
                    .build());
        }
        /*팀이름 임의로 50개 생성*/
        for (int i = 0; i < 50; i++) {
            teams.add(Team.builder()
                    .teamName(faker.team().name()) // 랜덤 팀 이름 생성
                    .build());
        }
        /*멤버 및 팀 저장*/
        memberRepository.saveAll(members);
        teamRepository.saveAll(teams);
        log.info("팀 및 멤버들의 더미데이터가 저장되었습니다.");
    }
}
