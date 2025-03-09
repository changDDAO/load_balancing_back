package com.changddao.load_balancing_back.service;

import com.changddao.load_balancing_back.dto.response.SingleResult;
import com.changddao.load_balancing_back.entity.Team;

import java.util.List;

public interface TeamService {
    String deleteTeam(Long id);
}
