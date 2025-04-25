package com.changddao.load_balancing_back.common.response;

import com.changddao.load_balancing_back.dto.response.MultipleResult;
import com.changddao.load_balancing_back.dto.response.Result;
import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.service.ResponseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseHandler implements ResponseService {
    /*단일데이터 결과값 구현체*/
    @Override
    public <T> SingleResult<T> handleSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    /*다중데이터 결과값 구현체*/
    @Override
    public <T> MultipleResult<T> handleListResult(List<T> list) {
        MultipleResult<T> result = new MultipleResult<>();
        result.setDatas(list);
        setSuccessResult(result);
        return result;
    }
    /*성공결과 구현체*/
    @Override
    public Result handleSuccessResult() {
        Result result = new Result();
        setSuccessResult(result);
        return result;
    }
    /*실패결과 구현체*/
    @Override
    public Result handleFailResult(int code, String msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }
    /*결과를 성공으로 setting 해줄 메서드*/
    private void setSuccessResult(Result result) {
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("success");
    }

}
