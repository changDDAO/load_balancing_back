package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Address;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.entity.Team;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import com.changddao.load_balancing_back.repository.team.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class MemberServiceImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("회원저장 테스트")
    void save() {
        //given
        Member member = Member.builder().name("changho").age(31)
                .address(new Address("대구", "수성구 청수로 261", "42114"))
                .build();
        //when
        Member saved = memberRepository.save(member);
        //then
        assertThat(saved.getName()).isEqualTo("changho");
        assertThat(saved.getAge()).isEqualTo(31);
        assertThat(saved.getMemberId()).isNotZero();
    }

    @Test
    @DisplayName("특정 연령대의 멤버 조회")
    void dynamicJpqlTest() {
        //given
        /*20 ~30 사이의 멤버들이 현재 285명이다. Console을 이용하여 조회*/
        MemberSearchDto cond = new MemberSearchDto();
        cond.setMinAge(20);
        cond.setMaxAge(30);
        List<Member> members = memberRepository.findAllMembersWithCond(cond);
        assertThat(members.size()).isEqualTo(285);
        //when
        //then
    }

    @Test
    @DisplayName("특정 연령대의 멤버 조희 페이징 테스트")
    void dynamicPagingTest() {
        //given
        MemberSearchDto cond = new MemberSearchDto();
        cond.setTeamName("제주");
        cond.setMinAge(20);
        cond.setMaxAge(30);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Member> members = memberRepository.findAllMembersWithConditions(cond, pageRequest);
        members.getContent().forEach(member ->
                log.info("\uD83D\uDD38 {} ({}세, {})", member.getName(), member.getAge(), member.getTeam().getTeamName())
        );
        //when
        //then
    }

    @Test
    @DisplayName("로그테스트")
    void logTest() {

        log.info("✅ INFO 로그 출력 테스트");
        log.debug("🛠 DEBUG 로그 출력 테스트");
        log.warn("⚠️ WARN 로그 출력 테스트");
        log.error("❌ ERROR 로그 출력 테스트");

    }
    @Test
    @DisplayName("연령별로 멤버 팀세팅 테스트")
    @Transactional
    void teamSettingTest() {
        //given
        Optional<Team> byId = teamRepository.findById(52L);
        Team team = byId.orElseThrow(() -> new RuntimeException("원하는 id를 가진 team이 없습니다"));
        Member changDDAO = memberRepository.findByName("changDDAO");
        changDDAO.setTeam(team);
        Member changMan= memberRepository.findByName("changDDAO");
        assertThat(changMan.getTeam()).isEqualTo(team);
    }
    @Test
    @DisplayName("멤버 이름변경 테스트")
    void changeMemberName(){
    //given
        Member changDDAO = memberRepository.findByName("changDDAO");
        changDDAO.changeName("changed");
        assertThat(changDDAO.getName()).isEqualTo("changed");
        //when
    //then
    }
    @Test
    @DisplayName("팀이름 변경 테스트")
    void changeTeamName(){
    //given
        List<Team> byTeamName = teamRepository.findByTeamNameContaining("제주");
        String teamName = byTeamName.stream().findAny().orElseThrow(()->new RuntimeException("찾을 수 있는 팀이없습니다."))
                .getTeamName();

        //when
        log.info("teamName:{}", teamName);
    //then
    }

}
