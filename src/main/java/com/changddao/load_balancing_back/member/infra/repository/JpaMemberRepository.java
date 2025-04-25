package com.changddao.load_balancing_back.member.infra.repository;

import com.changddao.load_balancing_back.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

    @Modifying
    @Query("delete from Member m where m.memberId in : memberIds")
    void deleteByMemberIds(@Param("memberIds") List<Long> memberIds);
}
