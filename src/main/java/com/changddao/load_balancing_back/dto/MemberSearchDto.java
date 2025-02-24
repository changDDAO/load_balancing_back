package com.changddao.load_balancing_back.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSearchDto {
    /*검색 이름*/
    private String name;
    /*최소 연령*/
    private int minAge;
    /*최대 연령*/
    private int maxAge;
}
