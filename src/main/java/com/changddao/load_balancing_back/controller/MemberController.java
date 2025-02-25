package com.changddao.load_balancing_back.controller;

import com.changddao.load_balancing_back.dto.MemberDto;
import com.changddao.load_balancing_back.dto.MemberSearchDto;
import com.changddao.load_balancing_back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @GetMapping("/v1/members")
    public Page<MemberDto> membersWithCond(MemberSearchDto cond,
                                           @RequestParam(value ="page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        cond.setMinAge(20);
        cond.setMaxAge(30);
        PageRequest pageRequest = PageRequest.of(page, size);
        return service.findAllWithTeam(cond, pageRequest);
    }
}
