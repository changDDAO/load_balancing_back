package com.changddao.load_balancing_back.common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MultipleResult<T> extends Result {
    List<T> datas;
}
