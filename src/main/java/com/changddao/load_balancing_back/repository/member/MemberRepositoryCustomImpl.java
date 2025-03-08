package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.changddao.load_balancing_back.entity.QMember.member;
import static com.changddao.load_balancing_back.entity.QTeam.team;


@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    /*동적쿼리를 이용하여 조건에 맞는 Member들만 조회한다.*/
    @Override
    public List<Member> findAllMembersWithCond(MemberSearchDto cond) {
        return queryFactory.select(member)
                .from(member)
                .leftJoin(member.team, team).fetchJoin()
                .where(searchPredicate(cond))
                .fetch();
    }


    @Override
    public Page<MemberDto> findAllMemberAndTeamsWithConditions(MemberSearchDto cond, Pageable pageable) {
        List<MemberDto> result = queryFactory.select(Projections.constructor(MemberDto.class,
                        member.memberId,
                        member.name,
                        member.address
                        , team.teamId,
                        team.teamName
                ))
                .from(member)
                .leftJoin(member.team, team)
                /*.fetchJoin() 얘같은 경우에는 Entity사이에서 fetch Type이 Lazy로 설정 돼있는 애들을 한번에 끌어오는거*/
                .where(searchPredicate(cond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Member> findAllMembersWithConditions(MemberSearchDto cond, Pageable pageable) {
        List<Member> result = queryFactory.selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(searchPredicate(cond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Member findByNameWithTeam(String name) {
        return queryFactory.selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .where(member.name.eq(name))
                .fetchOne();
    }
    /*동적 JPQL을 실행할 수 있도록 BooleanBuilder를 만들어준다.*/

    private BooleanBuilder searchPredicate(MemberSearchDto cond) {
        return new BooleanBuilder().and(memberNameEq(cond.getName()))
                .and(ageGoe(cond.getMinAge()))
                .and(ageLoe(cond.getMaxAge()))
                .and(teamNameEq(cond.getTeamName()));
    }

/*BooleanBuilder에 합쳐질 조각들*/

    private BooleanExpression memberNameEq(String name) {
        return name != null ? member.name.like(name) : null;
    }
    /*search condition에 minAge이상인 것들만 조회하기 위함*/
    private BooleanExpression ageGoe(Integer age) {
        return age!=null ? member.age.goe(age) : null;

    }
    /*search condition에 minAge이하인 것들만 조회하기 위함*/
    private BooleanExpression ageLoe(Integer age) {
        return age!=null ? member.age.loe(age) : null;
    }
    private BooleanExpression teamNameEq(String teamName) {
        return teamName != null ? team.teamName.like(teamName) : null;
    }
}
