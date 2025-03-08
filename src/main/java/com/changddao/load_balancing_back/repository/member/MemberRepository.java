package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Member findByName(String name);

    @Modifying
    @Query("delete from Member m where m.memberId in :memberIds")
    void deleteMemberByIds(@Param("memberIds") List<Long> memberIds);
}
