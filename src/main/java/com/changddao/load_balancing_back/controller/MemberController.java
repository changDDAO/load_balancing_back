package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Member;
import com.changddao.load_balancing_back.service.MemberService;
import com.changddao.load_balancing_back.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final ResponseService responseService;
    private final MemberService memberService;

    @GetMapping("/v1/members")
    public SingleResult<Page<MemberDto>> membersWithCond(MemberSearchDto cond,
                                                        @RequestParam(value ="page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        cond.setMinAge(20);
        cond.setMaxAge(30);
        PageRequest pageRequest = PageRequest.of(page, size);
        return responseService.handleSingleResult(memberService.findAllWithTeam(cond, pageRequest));
    }
    @PostMapping("/v1/member/join")
    public SingleResult<Member> joinMember(@RequestBody Member member) {
        return responseService.handleSingleResult(memberService.joinMember(member));
    }


}
