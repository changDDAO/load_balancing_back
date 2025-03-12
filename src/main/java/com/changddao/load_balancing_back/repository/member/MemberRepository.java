package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Member findByName(String name);

    /*CascadeType.ALL 속성 이용 시, 한방쿼리가 아닌 각 id마다 delete 쿼리가 생성되어
    * 한방쿼리로 삭제할 수 있는 최적화 쿼리 작성 */
    @Modifying
    @Query("delete from Member m where m.memberId in :memberIds")
    void deleteMemberByIds(@Param("memberIds") List<Long> memberIds);
}
