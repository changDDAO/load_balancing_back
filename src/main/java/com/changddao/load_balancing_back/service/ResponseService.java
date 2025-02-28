package com.changddao.load_balancing_back.service;

import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.dto.response.Result;
import com.changddao.load_balancing_back.dto.response.SingleResult;

import java.util.List;

public interface ResponseService {
    /*단일 결과 처리하기*/
    <T> SingleResult<T> handleSingleResult(T data);

    /*다중 결과 처리하기*/
    <T> MultipleResult<T> handleListResult(List<T> list);

    /*성공 결과 처리하기*/
    Result handleSuccessResult();

    /*실패 결과 처리하기*/
    Result handleFailResult(int code, String msg);

}
