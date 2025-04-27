package com.changddao.load_balancing_back.member.ui;

import com.changddao.load_balancing_back.member.domain.Address;
import com.changddao.load_balancing_back.member.domain.Member;
import com.changddao.load_balancing_back.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // ✅ 멤버 생성
    @PostMapping
    public void createMember(@RequestBody Member member) {
        memberService.createMember(member);
    }

    // ✅ 멤버 이름 변경
    @PutMapping("/{id}/name")
    public void changeMemberName(@PathVariable Long id,
                                 @RequestParam String newName) {
        memberService.changeMemberName(id, newName);
    }

    // ✅ 멤버 주소 변경
    @PutMapping("/{id}/address")
    public void changeAddress(@PathVariable Long id,
                              @RequestBody Address address) {
        memberService.changeAddress(id, address);
    }

    // ✅ 멤버 팀 변경
    @PutMapping("/{id}/team")
    public void changeTeam(@PathVariable("id") Long id,
                           @RequestParam("teamId") Long teamId) {
        memberService.changeTeam(id, teamId);
    }

    // ✅ 멤버 조회
    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMember(id);
    }
}
