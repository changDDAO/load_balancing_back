package com.changddao.load_balancing_back.domain.member.ui;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.dto.response.MultipleResult;
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

import java.util.List;

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
        PageRequest pageRequest = PageRequest.of(page, size);
        return responseService.handleSingleResult(memberService.findAllWithTeam(cond, pageRequest));
    }
    /*Paging없이 멤버 전체 조회하기, 데이터 양이 많지 않기 때문에 현재 테스트 용도로는 괜찮다.*/
    @PostMapping("/v1/members")
    public MultipleResult<MemberDto> getMembers(MemberSearchDto cond) {
        List<MemberDto> allMembers = memberService.findAllMembers(cond);
        return responseService.handleListResult(allMembers);
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
        memberService.deleteById(id);
        return responseService.handleSingleResult(id);
    }
    /*멤버 정보 변경*/
    @Transactional
    @PutMapping("/v1/member/{id}")
    public SingleResult<Member> changeMember(@PathVariable("id") Long id, @RequestBody Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("찾고자하는 멤버가 없습니다."));
        findMember.changeName(member.getName());
        return responseService.handleSingleResult(findMember);
    }



}
