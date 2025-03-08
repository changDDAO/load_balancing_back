package com.changddao.load_balancing_back.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MemberSearchDto {
    /*검색 이름*/
    private String name;
    /*최소 연령*/
    private Integer minAge;
    /*최대 연령*/
    private Integer maxAge;
    /*팀 이름*/
    private String teamName;

    private String pageNum;

}
