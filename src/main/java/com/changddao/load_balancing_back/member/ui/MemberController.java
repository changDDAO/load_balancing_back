package com.changddao.load_balancing_back.member.ui;

import com.changddao.load_balancing_back.member.domain.Member;
import com.changddao.load_balancing_back.member.domain.Address;
import com.changddao.load_balancing_back.member.domain.service.MemberService;
import com.changddao.load_balancing_back.member.ui.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 생성 (Builder 패턴 + Address 포함)
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(
            @RequestBody MemberRequest.Create request,
            UriComponentsBuilder uriBuilder) {

        Member member = Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .address(new Address(request.getCity(), request.getStreet(), request.getZipcode()))
                .build();

        memberService.createMember(member);

        URI location = uriBuilder.path("/api/members/{id}")
                .buildAndExpand(member.getMemberId())
                .toUri();

        return ResponseEntity.created(location).body(MemberResponse.from(member));
    }

    // 회원 이름 수정
    @PatchMapping("/{memberId}/name")
    public ResponseEntity<Void> changeName(
            @PathVariable Long memberId,
            @RequestBody MemberRequest.ChangeName request) {
        memberService.changeMemberName(memberId, request.getNewName());
        return ResponseEntity.ok().build();
    }

    // 회원 주소 수정
    @PatchMapping("/{memberId}/address")
    public ResponseEntity<Void> changeAddress(
            @PathVariable Long memberId,
            @RequestBody MemberRequest.ChangeAddress request) {
        Address newAddress = new Address(request.getCity(), request.getStreet(), request.getZipcode());
        memberService.changeAddress(memberId, newAddress); // MemberService에 changeAddress 메서드 추가 필요
        return ResponseEntity.ok().build();
    }

    // 회원 팀 변경 (Kafka 이벤트 발행)
    @PatchMapping("/{memberId}/team")
    public ResponseEntity<Void> changeTeam(
            @PathVariable Long memberId,
            @RequestBody MemberRequest.ChangeTeam request) {
        memberService.changeTeam(memberId, request.getTeamId());
        return ResponseEntity.ok().build();
    }

    // 회원 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
}