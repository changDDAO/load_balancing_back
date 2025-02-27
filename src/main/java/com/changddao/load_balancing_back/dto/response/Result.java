package com.changddao.load_balancing_back.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    /*응답 성공여부*/
    private boolean success = false;
    //응답코드
    private int code;
    /*응답 메세지*/
    private String msg;

}
