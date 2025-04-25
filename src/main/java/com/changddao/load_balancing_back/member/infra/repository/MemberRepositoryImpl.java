package com.changddao.load_balancing_back.member.infra.repository;

import com.changddao.load_balancing_back.member.domain.Member;
import com.changddao.load_balancing_back.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public Member findByName(String name) {
        return jpaMemberRepository.findByName(name);
    }

    @Override
    public void save(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public void deleteById(Long id) {
        jpaMemberRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> memberIds) {
        jpaMemberRepository.deleteByMemberIds(memberIds);
    }

    @Override
    public List<Member> findAll() {
        return jpaMemberRepository.findAll();
    }
}
