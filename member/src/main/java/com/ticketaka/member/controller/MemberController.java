package com.ticketaka.member.controller;


import com.ticketaka.member.dto.LoginRequestDto;
import com.ticketaka.member.dto.SignupRequestDto;
import com.ticketaka.member.dto.TokenInfo;
import com.ticketaka.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/login")
//    public ResponseEntity<TokenInfo> login(@RequestBody LoginRequestDto dto){
//        return memberService.login(dto);
//    }

    @GetMapping("/login")
    public String checkDuplicateMember(){
        return "duplicate";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequestDto dto){
        ResponseEntity<String> res = memberService.signUp(dto);
        return res;
    }

    @PostMapping("logout")
    public String logout(){
        return "logout";
    }
    @GetMapping("/adult")
    public String checkAdult(){
        return "adult";
    }




}
