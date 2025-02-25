package com.changddao.load_balancing_back.repository.member;

import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.changddao.load_balancing_back.entity.QMember.member;


@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> findAllMembersWithConditions(MemberSearchDto cond, Pageable pageable) {
        List<Member> result = queryFactory.selectFrom(member)
                .where(searchPredicate(cond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

/*동적 JPQL을 실행할 수 있도록 BooleanBuilder를 만들어준다.*/

    private BooleanBuilder searchPredicate(MemberSearchDto cond) {
        return new BooleanBuilder().and(memberNameEq(cond.getName()))
                .and(ageGoe(cond.getMinAge()))
                .and(ageLoe(cond.getMinAge()));


    }

/*BooleanBuilder에 합쳐질 조각들*/

    private BooleanExpression memberNameEq(String name) {
        return name != null ? member.name.eq(name) : null;
    }
    /*search condition에 minAge이상인 것들만 조회하기 위함*/
    private BooleanExpression ageGoe(Integer age) {
        return age!=null ? member.age.goe(age) : null;

    }
    /*search condition에 minAge이하인 것들만 조회하기 위함*/
    private BooleanExpression ageLoe(Integer age) {
        return age!=null ? member.age.loe(age) : null;

    }
}
