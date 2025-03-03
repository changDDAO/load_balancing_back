package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.repository.member.MemberRepository;
import com.changddao.load_balancing_back.service.MemberService;
import com.changddao.load_balancing_back.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberController {
    private final ResponseService responseService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    /*멤버 전체 조회*/
    @GetMapping("/v1/members")
    public SingleResult<Page<MemberDto>> membersWithCond(MemberSearchDto cond,
                                                        @RequestParam(value ="page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        cond.setMinAge(20);
        cond.setMaxAge(30);
        PageRequest pageRequest = PageRequest.of(page, size);
        return responseService.handleSingleResult(memberService.findAllWithTeam(cond, pageRequest));
    }
    /*멤버 단건 조회*/
    @GetMapping("/v1/member/{id}")
    public SingleResult<Member> findMember(@PathVariable("id") Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("찾고자 하는 멤버가 없습니다."));
        return responseService.handleSingleResult(findMember);
    }

    /*멤버 가입*/
    @Transactional
    @PostMapping("/v1/member/join")
    public SingleResult<Member> joinMember(@RequestBody Member member) {
        return responseService.handleSingleResult(memberService.joinMember(member));
    }
    /*멤버 삭제*/
    @Transactional
    @DeleteMapping("/v1/member/{id}")
    public SingleResult<Long> deleteMember(@PathVariable("id") Long id) {
        deleteMember(id);
        return responseService.handleSingleResult(id);
    }
    /*멤버 정보 변경*/
    @Transactional
    @PutMapping("/v1/member/{id}")
    public SingleResult<Member> changeMember(@RequestBody Member member) {
        Member findMember = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new RuntimeException("찾고자하는 멤버가 없습니다."));
        findMember.changeName(member.getName());
        return responseService.handleSingleResult(findMember);
    }



}
