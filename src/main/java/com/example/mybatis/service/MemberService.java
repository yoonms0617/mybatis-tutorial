package com.example.mybatis.service;

import com.example.mybatis.dto.MemberDetailResponse;
import com.example.mybatis.dto.MemberListResponse;
import com.example.mybatis.dto.MemberSaveRequest;
import com.example.mybatis.dto.MemberUpdateRequest;

import com.example.mybatis.exception.DuplicateEmailException;
import com.example.mybatis.mapper.MemberMapper;

import com.example.mybatis.model.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper  memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Transactional
    public void save(MemberSaveRequest request) {
        if (isExistsEmail(request.getEmail())) {
            throw new DuplicateEmailException("사용 중인 이메일입니다.");
        }
        Member member = new Member(request.getName(), request.getEmail());
        memberMapper.save(member);
    }

    private boolean isExistsEmail(String email) {
        return memberMapper.isExistsEmail(email);
    }

    @Transactional(readOnly = true)
    public MemberDetailResponse memberFindById(Long id) {
        Member member = memberMapper.findById(id);
        return new MemberDetailResponse(member);
    }

    @Transactional(readOnly = true)
    public MemberDetailResponse memberFindByEmail(String email) {
        Member member = memberMapper.findByEmail(email);
        return new MemberDetailResponse(member);
    }

    @Transactional(readOnly = true)
    public MemberListResponse memberFindAll() {
        List<Member> memberList = memberMapper.findAll();
        return new MemberListResponse(memberList);
    }

    @Transactional
    public MemberDetailResponse memberUpdate(MemberUpdateRequest request, Long id) {
        Member member = memberMapper.findById(id);
        member.update(request.getName(), request.getEmail());
        memberMapper.update(member);
        return new MemberDetailResponse(member);
    }

    @Transactional
    public void memberDelete(Long id) {
        memberMapper.delete(id);
    }

}
