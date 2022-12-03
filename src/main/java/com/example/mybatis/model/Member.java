package com.example.mybatis.model;

import lombok.Getter;

@Getter
public class Member {

    private Long id;

    private String name;

    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
