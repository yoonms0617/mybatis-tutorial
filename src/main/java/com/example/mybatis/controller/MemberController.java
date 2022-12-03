package com.example.mybatis.controller;

import com.example.mybatis.dto.MemberDetailResponse;
import com.example.mybatis.dto.MemberListResponse;
import com.example.mybatis.dto.MemberSaveRequest;
import com.example.mybatis.dto.MemberUpdateRequest;
import com.example.mybatis.service.MemberService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> memberSave(@RequestBody MemberSaveRequest request) {
        memberService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<MemberDetailResponse> memberFindByEmail(@PathVariable("email") String email) {
        MemberDetailResponse response = memberService.memberFindByEmail(email);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<MemberDetailResponse> memberFindById(@PathVariable("id") Long id) {
        MemberDetailResponse response = memberService.memberFindById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/find-all")
    public ResponseEntity<MemberListResponse> memberFindAll() {
        MemberListResponse response = memberService.memberFindAll();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberDetailResponse> memberUpdate(@PathVariable("id") Long id,
                                                             @RequestBody MemberUpdateRequest request) {
        MemberDetailResponse response = memberService.memberUpdate(request, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> memberDelete(@PathVariable("id") Long id) {
        memberService.memberDelete(id);
        return ResponseEntity.ok().build();
    }

}
