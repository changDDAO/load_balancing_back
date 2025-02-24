package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
