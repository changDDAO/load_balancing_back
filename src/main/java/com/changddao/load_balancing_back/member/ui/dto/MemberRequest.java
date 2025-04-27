package com.changddao.load_balancing_back.member.ui.dto;

import lombok.Getter;

public class MemberRequest {

    @Getter
    public static class Create {
        private String name;
        private Integer age;
        private String city;      // Address 필드
        private String street;    // Address 필드
        private String zipcode;  // Address 필드
    }

    @Getter
    public static class ChangeName {
        private String newName;
    }

    @Getter
    public static class ChangeAddress {
        private String city;
        private String street;
        private String zipcode;
    }

    @Getter
    public static class ChangeTeam {
        private Long teamId;
    }
}