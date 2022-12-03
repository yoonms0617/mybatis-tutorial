package com.example.mybatis.dto;

import com.example.mybatis.model.Member;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberListResponse {

    private final List<MemberList> memberList;

    public MemberListResponse(List<Member> memberList) {
        this.memberList = memberList.stream()
                .map(member -> new MemberList(
                        member.getId(),
                        member.getName(),
                        member.getEmail()
                )).toList();
    }

    @Getter
    private static class MemberList {

        private final Long id;

        private final String name;

        private final String email;

        public MemberList(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

    }

}
