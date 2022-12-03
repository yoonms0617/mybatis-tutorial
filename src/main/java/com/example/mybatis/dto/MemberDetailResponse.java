package com.example.mybatis.dto;

import com.example.mybatis.model.Member;

import lombok.Getter;

@Getter
public class MemberDetailResponse {

    private final Long id;

    private final String name;

    private final String email;

    public MemberDetailResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }

}
