package com.changddao.load_balancing_back.dto;

import com.changddao.load_balancing_back.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberDto {
    private Long memberId;
    private String name;
    private Address address;
    private Long teamId;
    private String teamName;

}
