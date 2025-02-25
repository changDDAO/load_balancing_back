package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.entity.Address;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    @DisplayName("회원저장 테스트")
    void save(){
    //given
        Member member = Member.builder().name("changho").age(31)
                .address(new Address("대구", "수성구 청수로 261", "42114"))
                .build();
        //when
        Member saved = memberRepository.save(member);
        //then
        Assertions.assertThat(saved.getName()).isEqualTo("changho");
        Assertions.assertThat(saved.getAge()).isEqualTo(31);
        Assertions.assertThat(saved.getMemberId()).isNotZero();
    }

}
