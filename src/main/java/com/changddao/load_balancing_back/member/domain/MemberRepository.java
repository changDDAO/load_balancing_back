package com.changddao.load_balancing_back.member.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);

    Member findByName(String name);

    void save(Member member);

    void deleteById(Long id);

    void deleteByIds(List<Long> memberIds);

    List<Member> findAll();

}
