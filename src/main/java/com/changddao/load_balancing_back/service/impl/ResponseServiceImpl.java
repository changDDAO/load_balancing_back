package com.changddao.load_balancing_back.service.impl;

import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.dto.response.Result;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.service.ResponseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {
    @Override
    public <T> SingleResult<T> handleSingleResult(T data) {
        return null;
    }

    @Override
    public <T> MultipleResult<T> handleListResult(List<T> list) {
        return null;
    }

    @Override
    public Result handleSuccessResult() {
        return null;
    }

    @Override
    public Result handleFailResult(int code, String msg) {
        return null;
    }
    /*결과를 성공으로 setting 해줄 메서드*/
    private void setSuccessResult(Result result) {
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("success");
    }

}
